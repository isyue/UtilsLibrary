package coszero.utilslibrary.base;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * ListView或GrideView的Adapter父类
 */
public abstract class AdapterRecyclerBase<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List list;
    protected Context context;
    protected LayoutInflater inflater;

    public AdapterRecyclerBase(Context context, List list) {
        this.list = list;
        this.context = context;
        this.inflater = inflater.from(context);
    }

    protected <V> V findView(Activity paramActivity, int paramInt) {
        return (V) paramActivity.findViewById(paramInt);
    }

    protected <V> V findView(View paramView, int paramInt) {
        return (V) paramView.findViewById(paramInt);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //替换数据源
    public void setData(List data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public void refresh(List data) {
        if (data != null) {
            if (data.size() > 0) {
                if (list == data) { // 两个list 是同一个
                    notifyDataSetChanged();
                    return;
                } else {
                    list.clear();
                    list.addAll(data);
                }
            } else {
                list.clear();
            }
        } else {
            list.clear();
        }
        notifyDataSetChanged();
    }


    /**
     * 清理List集合
     */
    public void clean() {
        list.clear();
        notifyDataSetChanged();
    }

    /**
     * 添加集合
     *
     * @param list
     */
    public void addAll(List list) {
        if (list != null) {
            if (list.size() > 0) {
                if (this.list.equals(list)) { // 两个list 是同一个
                    notifyDataSetChanged();
                    return;
                } else {
                    this.list.addAll(list);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 添加集合到指定位置
     *
     * @param list
     * @param position
     */
    public void addAll(List list, int position) {
        this.list.addAll(position, list);
        notifyDataSetChanged();
    }

    /**
     * 返回List集合
     *
     * @return
     */
    public List getList() {
        return list;
    }

}
