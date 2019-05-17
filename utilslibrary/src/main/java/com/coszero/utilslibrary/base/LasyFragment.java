package com.coszero.utilslibrary.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coszero.utilslibrary.utils.LogX;


/**
 * Created by ydh on 2015/12/29.
 */
@SuppressLint("ValidFragment")
public abstract class LasyFragment extends Fragment implements View.OnClickListener {
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    protected View view;


    // 这个方法在onCreateView 之前调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        view = initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化布局
     *
     * @return view
     */
    protected abstract View initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void onVisible() {
        lasyLoad();
        LogX.e("LasyFragment", "状态为可见");
    }

    protected void onInvisible() {
        LogX.e("LasyFragment", "状态为不可见");
    }

    public void lasyLoad() {
    }


    protected <V> V findView(int paramInt) {
        return (V) getActivity().findViewById(paramInt);
    }

    protected <V> V findView(View paramView, int paramInt) {
        return (V) paramView.findViewById(paramInt);
    }

    protected void setOnclick(int id) {
        getActivity().findViewById(id).setOnClickListener(this);
    }

    protected void setOnclick(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    public abstract String getTitle();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
