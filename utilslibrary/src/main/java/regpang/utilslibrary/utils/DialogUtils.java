package regpang.utilslibrary.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 * Created by zhangfeng on 2015/12/8.
 */
public class DialogUtils {
    private ProgressDialog progressDialog;
    private Context context;
    private String content;

    public DialogUtils(Context context, String content) {
        this.context = context;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void showPD() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
            progressDialog.setTitle(null);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(content);
        progressDialog.show();
    }

    public void dismissPD() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
