package me.funnyzhao.mangostreet.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.melnykov.fab.FloatingActionButton;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.disposeitem.IItemDetailsPer;
import me.funnyzhao.mangostreet.presenter.disposeitem.ItemDetailsPerImpl;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.dmview.IItemDetailsView;

/**
 * Created by funnyzhao .
 * 物品详情页
 */

public class ItemDetailsActivity extends BaseActivity implements IItemDetailsView,View.OnClickListener {
    //UI
    @BindView(R.id.item_iv_back)
    ImageView ivBack;
    @BindView(R.id.item_iv_itemImage)
    ImageView ivImage;
    @BindView(R.id.item_like)
    FloatingActionButton fabLike;
    @BindView(R.id.itme_tv_description)
    TextView tvDescription;
    @BindView(R.id.itme_tv_address)
    TextView tvAddress;
    @BindView(R.id.item_tv_title)
    TextView tvTitle;
    @BindView(R.id.item_tv_price)
    TextView tvPrice;

    @BindView(R.id.item_xfv_user)
    CircleImageView circleImage;
    @BindView(R.id.item_tv_username)
    TextView tvUserName;
    @BindView(R.id.item_tv_department)
    TextView tvDepartment;
    @BindView(R.id.item_tv_tel)
    TextView tvTel;
    @BindView(R.id.item_tv_qq)
    TextView tvQQ;

    //P
    private IItemDetailsPer iItemDetailsPer;

    //M
    private Item mItem;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_item_details);
    }

    @Override
    protected void initEvents() {
        getItem();
        setBack();
        setFabLike();
        setItemInfo();
        if (NetWorkUtil.isNetConnect(this)){
            iItemDetailsPer.loadUserInfo();
        }else {
            showNetWorkInfo();
        }
    }

    @Override
    protected void initDataOrPresenter() {
        iItemDetailsPer=new ItemDetailsPerImpl(this);
    }

    @Override
    public Item getItem() {
        Intent intent=getIntent();
        Item item=(Item) intent.getSerializableExtra("item");
        mItem=item;
        return item;
    }


    @Override
    public void setItemInfo() {
        Glide.with(this)
                .load(mItem.getItemImage())
                .centerCrop()
                .error(R.mipmap.user_icon_error)
                .into(ivImage);
        tvDescription.setText(mItem.getItemDescription());
        tvAddress.setText(mItem.getItemAddress());
        tvTitle.setText(mItem.getItemName());
        tvPrice.setText(mItem.getPrice());
    }

    @Override
    public void setUserInfo(_User userInfo) {
        Glide.with(this)
                .load(userInfo.getImageurl())
                .centerCrop()
                .error(R.mipmap.user_icon_error)
                .into(circleImage);
        tvUserName.setText(userInfo.getUsername());
        if (userInfo.getDepartment()==null){
            tvDepartment.setText("未填写");
        }else {
            tvDepartment.setText(userInfo.getDepartment());
        }
        if (userInfo.getTel()==null){
            tvTel.setText("未填写电话");
        }else {
            tvTel.setText(userInfo.getTel());
        }
        if(userInfo.getTentqq()==null){
            tvQQ.setText("未填写QQ");
        }else {
            tvQQ.setText(userInfo.getTentqq());
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkInfo() {
        Toast.makeText(this,"无网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    public void setBack() {
        ivBack.setOnClickListener(this);
    }
    public void setFabLike() {
        fabLike.setOnClickListener(this);
    }
}
