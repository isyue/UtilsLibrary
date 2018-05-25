package regpang.utilslibrary.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


/**
 * 内部判断App是否升级的管理类
 * 当需要升级时，不调用外部下载器，直接使用内部下载器下载应用。
 *@author coolszy
 *@date 2012-4-26
 *@blog http://blog.92coding.com
 */
public class UpdateManager
{
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
        };
    };

    private Handler mHandlerIsUpdate = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    showNoticeDialog();
                    break;
                case 2:

                    //if(mContext.getClass().getSimpleName().equals("SettingFragment")){

                    Toast.makeText(mContext, "已经是最新版本!", Toast.LENGTH_LONG).show();
                    //}
                    break;
                default:
                    break;
            }
        };
    };

    public UpdateManager(Context context)
    {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(int ShowMsg)
    {
        new MyThread(ShowMsg) {
            @Override
            public void run() {

                // 获取当前软件版本
                int versionCode = getVersionCode(mContext);
                // 把version.xml放到网络上，然后获取文件信息
                //InputStream inStream = ParseXmlService.class.getClassLoader().getResourceAsStream(R.string.server_url + "version.xml");


                URL this_url;
                InputStream inStream = null;
                try {
//                    下载地址
                    this_url = new URL("http://" + "/c/version.xml");
                    inStream = this_url.openConnection().getInputStream();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


                // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析
//                ParseXmlService service = new ParseXmlService();
                try
                {
//                    mHashMap = service.parseXml(inStream);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (null != mHashMap)
                {
                    int serviceCode = Integer.valueOf(mHashMap.get("version"));
                    // 版本判断
                    if (serviceCode > versionCode)
                    {
                        mHandlerIsUpdate.sendEmptyMessage(1);
                    }
                    else{
                        if(GetShowMsg()==1)
                        {
                            mHandlerIsUpdate.sendEmptyMessage(2);
                        }
                    }
                }
                else{
                    if(GetShowMsg()==1)
                    {
                        mHandlerIsUpdate.sendEmptyMessage(2);
                    }
                }
            }
        }.start();
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context)
    {
        int versionCode = 0;
        try
        {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.db.surfing_car_friend", 0).versionCode;
        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog()
    {
        // 构造对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("软件更新");
        //builder.setMessage(R.string.soft_update_info);
        builder.setMessage(mHashMap.get("content").toString());
        // 更新
        builder.setPositiveButton("确定更新", new OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
            }
        });
        // 稍后更新
        builder.setNegativeButton("稍后更新", new OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog()
    {
        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("更新提醒");
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        //重新设置下载进度条
        View v = inflater.inflate(1, null);
        mProgress = (ProgressBar) v.findViewById(android.R.id.progress);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton("取消", new OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // 设置取消状态
                cancelUpdate = true;
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 现在文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk()
    {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     *@date 2012-4-26
     *@blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(mHashMap.get("url"));
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists())
                    {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mHashMap.get("name"));
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do
                    {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0)
                        {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    };

    /**
     * 安装APK文件
     */
    private void installApk()
    {
        File apkfile = new File(mSavePath, mHashMap.get("name"));
        if (!apkfile.exists())
        {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

    public class MyThread extends Thread
    {
        private int ShowMsg;
        public MyThread(int _ShowMsg)
        {
            ShowMsg=_ShowMsg;
        }
        public int GetShowMsg()
        {
            return ShowMsg;
        }
    }
}