package regpang.utilslibrary.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 请求等待提示工具类
 * 只会在页面第一次请求时出现，需要再次出现，需要调用 reset()方法
 * Created by zhangfeng on 2015/12/8.
 */
public class LoadingUtils {
    private ProgressDialog progressDialog;
    private Context context;
    private String content;
    private boolean isFirst = true;

    public LoadingUtils(Context context, String content) {
        this.context = context;
        this.content = content;
    }

    /**
     * 获取展示的内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置需要在等待时显示的文字
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 显示等待提示
     */
    public void showPD() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
            progressDialog.setTitle(null);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(content);
        if (isFirst) {
            progressDialog.show();
            isFirst = false;
        }
    }

    /**
     * 重置显示
     */
    public void reset() {
        isFirst = true;
    }

    /**
     * 提示消失
     */
    public void dismissPD() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
