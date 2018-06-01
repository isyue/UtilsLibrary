package regpang.utilslibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import regpang.utilslibrary.R;
import regpang.utilslibrary.utils.LoadingUtils;
import regpang.utilslibrary.utils.LogX;


/**
 * Fragment的父类
 * Created by yue on 2016-05-23.
 */
public abstract class BaseFragment extends Fragment {
    public View view;
    protected LoadingUtils loadingUtils;
    protected Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingUtils = new LoadingUtils(getActivity(), getActivity().getString(R.string.dialog_request_data));
        view = getView();
        LogX.e("view","view");
        return view;
    }


    public abstract View getView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findID();
        initView();
    }

    protected abstract void findID();

    protected abstract void initView();


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
