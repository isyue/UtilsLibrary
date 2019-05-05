package com.coszero.utilslibrary.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.coszero.utilslibrary.callback.OnItemClickListener;


/**
 * ClassName: BaseRecyclerAdapter<p>
 * Author:oubowu<p>
 * Fuction: RecyclerView通用适配器<p>
 *     使用空布局时，需要传入空布局
 * CreateDate:2016/2/16 22:47<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public abstract class BaseRecyclerAdapter<T> extends AdapterRecyclerBase<BaseRecyclerViewHolder> {


    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_EMPTY = 2;

    protected List<T> mData;
    protected Context mContext;
    protected boolean mUseAnimation;
    protected LayoutInflater mInflater;
    protected OnItemClickListener mClickListener;
    protected boolean mShowFooter;
    protected boolean mShowEmpty = false;
    protected int mStatus = 0;


    private RecyclerView.LayoutManager mLayoutManager;

    private int mLastPosition = -1;
    //数据为空显示的图片
    private int emptyImage = 0;
    private String emptyStr = "抱歉，还没有数据哟~";
    //设在是一个空布局
    private int emptyLayoutId = 0;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this(context, data, true);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, boolean useAnimation) {
        this(context, data, useAnimation, null, 0);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, int status) {
        this(context, data, true, null, status);
    }

    public BaseRecyclerAdapter(Context context, List<T> data,
                               boolean useAnimation,
                               RecyclerView.LayoutManager layoutManager, int status) {
        super(context, data);
        mContext = context;
        mUseAnimation = useAnimation;
        mLayoutManager = layoutManager;
        mData = data == null ? new ArrayList<T>() : data;
        mInflater = LayoutInflater.from(context);
        mStatus = status;
    }

    public void setEmptyLayoutId(int emptyLayoutId) {
        this.emptyLayoutId = emptyLayoutId;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new BaseRecyclerViewHolder(mContext,
                    mInflater.inflate(emptyLayoutId, parent, false));
        } else if (viewType == TYPE_EMPTY) {
            return new BaseRecyclerViewHolder(mContext, mInflater.inflate(emptyLayoutId, parent, false));
        } else {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext,
                    mInflater.inflate(getItemLayoutId(viewType), parent, false));
            if (mClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(v, holder.getLayoutPosition());
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mClickListener.onItemLongClick(v, holder.getLayoutPosition());
                        return true;
                    }
                });
            }
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (mLayoutManager != null) {
                if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                    if (((StaggeredGridLayoutManager) mLayoutManager).getSpanCount() != 1) {
                        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView
                                .getLayoutParams();
                        params.setFullSpan(true);
                    }
                } else if (mLayoutManager instanceof GridLayoutManager) {
                    if (((GridLayoutManager) mLayoutManager)
                            .getSpanCount() != 1 && ((GridLayoutManager) mLayoutManager)
                            .getSpanSizeLookup() instanceof GridLayoutManager.DefaultSpanSizeLookup) {
                        throw new RuntimeException("网格布局列数大于1时应该继承SpanSizeLookup时处理底部加载时布局占满一行。");
                    }
                }
            }
        } else if (getItemViewType(position) == TYPE_EMPTY) {
            if (mShowEmpty) {
                /*ImageView ivImage = holder.getImageView(R.id.iv_image);
                ivImage.setVisibility(View.VISIBLE);
                ivImage.setImageResource(emptyImage);
                TextView tvText = holder.getTextView(R.id.tv_text);
                tvText.setVisibility(View.VISIBLE);
                tvText.setText(emptyStr);*/
            }
//            bindData(holder, position, null);
//            showEmpty();
        } else {
            bindData(holder, position, mData.get(position));
//            if (mUseAnimation) {
//                setAnimation(holder.itemView, position);
//            }
        }
    }

    protected void setAnimation(View viewToAnimate, int position) {
//        if (position > mLastPosition) {
//            Animation animation = AnimationUtils
//                    .loadAnimation(viewToAnimate.getContext(), R.anim.item_bottom_in);
//            viewToAnimate.startAnimation(animation);
//            mLastPosition = position;
//        }
    }

    @Override
    public void onViewDetachedFromWindow(BaseRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
//        if (mUseAnimation && holder.itemView.getAnimation() != null && holder.itemView
//                .getAnimation().hasStarted()) {
//            holder.itemView.clearAnimation();
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmptyLs(mData)) {
            return TYPE_EMPTY;
        }
        if (mShowFooter && getItemCount() - 1 == position) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int i = mShowFooter ? 1 : 0;
        // KLog.e("插入: "+i);
        return !isEmptyLs(mData) ? mData.size() + i : 1;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public abstract int getItemLayoutId(int viewType);

    public abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);

//    protected int bindViewType(int position) {
//        return 0;
//    }

    /**
     * 设置空布局要显示的内容
     *
     * @param imageRes 图片资源id,不改变可传0
     * @param msg 描述信息，不改变可传空
     */
    public void showEmpty(int imageRes, String msg) {
        if (imageRes != 0) {
            emptyImage = imageRes;
        }
        if (!TextUtils.isEmpty(msg)) {
            emptyStr = msg;
        }
        mShowEmpty = true;
        mShowFooter = false;
        notifyDataSetChanged();
    }

    public void showEmpty() {
        showEmpty(0, "");
    }

    public void hideEmpty() {
        mShowEmpty = false;
        mShowFooter = false;
        notifyDataSetChanged();
    }

    public void showFooter() {
        // KLog.e("Adapter显示尾部: " + getItemCount());
        notifyItemInserted(getItemCount());
        mShowFooter = true;
        mShowEmpty = false;
    }

    public void hideFooter() {
        // KLog.e("Adapter隐藏尾部:" + (getItemCount() - 1));
        notifyItemRemoved(getItemCount() - 1);
        mShowFooter = false;
        mShowEmpty = false;
    }

    public static <V> boolean isEmptyLs(List<V> list) {
        return null == list || list.size() <= 0;
    }

}
