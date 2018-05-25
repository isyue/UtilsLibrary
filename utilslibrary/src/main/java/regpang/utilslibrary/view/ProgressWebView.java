package regpang.utilslibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import regpang.utilslibrary.R;


/**
 * 带有进度条的webView浏览器
 * Created by AndroidDeveloper on 2016/9/8 0008.
 */
public class ProgressWebView extends WebView {

    private final ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,//设置进度条为横向
                android.R.attr.progressBarStyleHorizontal);
        //设置进度条高度
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                3, 0, 0));
        //设置进度条样式和颜色
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_webview_bar_states);
        progressbar.setProgressDrawable(drawable);
        //将进度条添加进入父布局
        addView(progressbar);
        //设置浏览器不跳转外部浏览器
        setWebViewClient(new WebViewClient() {
        });
        //设置浏览器支持javaScrip交互
        getSettings().setJavaScriptEnabled(true);
        //设置浏览器使用的内核
        setWebChromeClient(new WebChromeClient());
//是否支持缩放
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
