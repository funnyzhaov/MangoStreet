package me.funnyzhao.mangostreet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 * 物品RecyclerView Adapter
 */

public class CollectGoodsAdapter extends RecyclerView.Adapter<CollectGoodsAdapter.ViewHolder>implements View.OnClickListener{
    //物品List
    private List<Item> mItemList=new ArrayList<>();
    private Context context;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public CollectGoodsAdapter(List<Item> data, Context context){
        mItemList = data;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_goods_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mItemList.get(position).getItemName());
        holder.tvAddress.setText(mItemList.get(position).getItemAddress());
        holder.tvPrice.setText(mItemList.get(position).getPrice());
        holder.tvDescription.setText(mItemList.get(position).getItemSubDescription());
        //设置tag
        holder.itemView.setTag(position);
        Glide.with(context)
                .load(mItemList.get(position).getItemImage())
                .centerCrop()
                .error(R.mipmap.user_icon_error)
                .into(holder.iv_image);
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //getTag获取的即是点击位置
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_image)
        ImageView iv_image;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_description)
        TextView tvDescription;

        @BindView(R.id.tv_itemAddress)
        TextView tvAddress;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 内部点击接口
     */
    public static interface OnRecyclerViewItemClickListener {
        /**
         * item点击
         * @param view
         * @param position  当前位置
         */
        void onItemClick(View view, int position);
    }

    /**
     * 设置点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
