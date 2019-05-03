package coszero.utilslibrary.base.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.List;

import coszero.utilslibrary.base.BaseAdapter;

/**
 * Created by FuJinhu on 2017-1-23.
 * 统一样式的刷新,配合ListView
 */
public class AdapterRefresh<T> implements AdapterRefreshInterface<T> {
    private boolean isUp;
    private boolean isDown;
    private int rows;
    private Context context;
    private RecyclerView moreRecyclerView;
    private BaseAdapter<T> adapterBase;
    private int imageRes = 0;//空布局图标
    private String emptyStr = "";//空布局消息

    /**
     * @param context
     * @param isUp 下拉刷新
     * @param isDown 上拉加载
     * @param rows
     */
    public AdapterRefresh(Context context, boolean isUp, boolean isDown, int rows) {
        this.isUp = isUp;
        this.isDown = isDown;
        this.rows = rows;
        this.context = context;
    }

    public AdapterRefresh(Context context, boolean isUp, boolean isDown) {
        this(context, isUp, isDown, 10);
    }

    public void init(RecyclerView moreRecyclerView, BaseAdapter<T> adapterBase) {
        this.moreRecyclerView = moreRecyclerView;
        this.adapterBase = adapterBase;
//        moreRecyclerView.setPullRefreshEnable(isUp);//禁止下拉刷新
//        moreRecyclerView.setPushRefreshEnable(isDown);//禁止上拉刷新
    }

    public void doRefresh(List<T> list, int page) {
        if (null != list) {
            if (list.size() >= rows && isUp) {
//                moreRecyclerView.setPushRefreshEnable(true);
            } else {
//                moreRecyclerView.setPushRefreshEnable(false);
            }
            if (page == 1) {
                if (list.size() == 0) {
//                    ToastUtils.showMsg(context, "暂无数据");
                    showEmpty();
                }
                adapterBase.refresh(list);
            } else {
                if (list.size() > 0) {
                    adapterBase.addAll(list);
                } else {
//                    ToastUtils.showMsg(context, "暂无更多数据");
                }
            }
        } else {
//            ToastUtils.showMsg(context, "暂无数据");
        }
    }

    public void complete() {
//        moreRecyclerView.setPullLoadMoreCompleted();
    }

    public void refresh() {
//        moreRecyclerView.refresh();
    }

    private void showEmpty() {
        if (imageRes != 0 || !TextUtils.isEmpty(emptyStr)) {
//            adapterBase.showEmpty(imageRes, emptyStr);
        } else {
//            adapterBase.showEmpty();
        }
    }

    /**
     * 设置空布局要显示的内容
     *
     * @param imageRes 图片资源id,不改变可传0
     * @param msg 描述信息，不改变可传空
     */
    public void showEmpty(int imageRes, String msg) {
        if (imageRes != 0) {
            this.imageRes = imageRes;
        }
        if (!TextUtils.isEmpty(msg)) {
            emptyStr = msg;
        }
    }
}
