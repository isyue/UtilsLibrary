package com.coszero.uilibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

public class MyToolBar extends Toolbar {
    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
