package xmqian.myutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import regpang.utilslibrary.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v){
        ToastUtils.showLongMsg(this,"哈哈哈哈哈哈哈");
    }
}
