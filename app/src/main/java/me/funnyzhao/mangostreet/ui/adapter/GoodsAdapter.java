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

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder>{
    //物品List
    private List<Item> mItemList=new ArrayList<>();
    //截取后
    private Context context;
    public GoodsAdapter(List<Item> data,Context context){
        mItemList = data;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ft_goods_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mItemList.get(position).getItemName());
        holder.tvAddress.setText(mItemList.get(position).getItemAddress());
        holder.tvPrice.setText(mItemList.get(position).getPrice());
        holder.tvDescription.setText(mItemList.get(position).getItemDescription());
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
}
