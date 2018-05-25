package regpang.utilslibrary.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * List或Gride的Adapter父类
 * Created by ydh on 2015/8/24.
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    protected List<T> list;
    protected Context context;

    public BaseAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    protected <V> V findView(Activity paramActivity, int paramInt) {
        return (V) paramActivity.findViewById(paramInt);
    }

    protected <V> V findView(View paramView, int paramInt) {
        return (V) paramView.findViewById(paramInt);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public T getItem(int i) {
        if (getCount() != 0) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public abstract View getView(int position, View convertView, ViewGroup viewGroup);

    public void refresh(List<T> data) {
        if (data != null) {
            if (data.size() > 0) {
                if (list.equals(data)) { // 两个list 是同一个
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
    public void addAll(List<T> list) {
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
    public void addAll(List<T> list, int position) {
        this.list.addAll(position, list);
        notifyDataSetChanged();
    }

    /**
     * 返回List集合
     *
     * @return
     */
    public List<T> getList() {
        return list;
    }

}
