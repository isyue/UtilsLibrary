package com.coszero.utilslibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coszero.utilslibrary.utils.LogX;


/**
 * Fragment的父类
 * Created by yue on 2016-05-23.
 *
 * @deprecated 没啥需要统一管理的，自己写吧
 */
public abstract class BaseFragment extends Fragment {
    public View view;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            View layout = inflater.inflate(getLayoutResource(), container, false);
            return layout;
        } catch (Exception e) {
            String errorMsg = "布局使用异常，移除类：" + this.getClass().getSimpleName() + " 异常布局ID:" + getLayoutResource() +
                    " 异常信息：" + e.toString();
            LogX.e("### BaseFragment", errorMsg);
            new Throwable(errorMsg);
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findID();
        initView();
    }

    protected abstract void findID();

    protected abstract void initView();

    /**
     * 返回布局ID
     */
    protected abstract @LayoutRes
    int getLayoutResource();

    /**
     * 自动转控件类型
     *
     * @param paramInt
     * @param <V>
     * @return
     */
    protected <V> V findView(int paramInt) {
        return (V) getActivity().findViewById(paramInt);
    }

    /**
     * 自动转控件类型
     *
     * @param paramView
     * @param paramInt
     * @param <V>
     * @return
     */
    protected <V> V findView(View paramView, int paramInt) {
        return (V) paramView.findViewById(paramInt);
    }
}
