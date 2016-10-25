package me.funnyzhao.mangostreet.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.melnykov.fab.FloatingActionButton;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Collect;
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

    private Handler mHandler=new Handler(Looper.getMainLooper());

   private Collect[] collects;
    boolean isColleted=false;
    private String nowCollectId;
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
            iItemDetailsPer.loadCollectAll();
        }else {
            showNetWorkInfo();
        }
    }

    @Override
    protected void initDataOrPresenter() {
        iItemDetailsPer=new ItemDetailsPerImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MangoApplication.clearMap();
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .fitCenter()
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
        switch (v.getId()){
            case R.id.item_like:
                disposeFabLike(mItem.getObjectId());
                break;
            case R.id.item_iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void setBack() {
        ivBack.setOnClickListener(this);
    }
    private void setFabLike() {
        fabLike.setOnClickListener(this);
    }

    /**
     * 检查用户是否在线
     */
    private boolean checkOnline(){
        Intent intent=getIntent();
        int tag=intent.getIntExtra("tag",0);
        if (tag==1||tag==0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 检查当前物品是否被当前用户收藏
     * @return
     */
    private boolean checkItemCollected(){
        //然后找出是否有当前物品
        iItemDetailsPer.loadCollectAll();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                collects=iItemDetailsPer.getCollects();
                for (int i=0;i<collects.length;i++){
                    if (nowCollectId==null){
                        if(collects[i].getUserObjectId().equals(MangoApplication.getUser().getObjectId()) &&
                                collects[i].getItemId().equals(mItem.getObjectId())){
                            isColleted=true;
                            nowCollectId=collects[i].getObjectId();
                            break;
                        }
                    }else {
                        if (nowCollectId.equals(collects[i].getObjectId())){
                            isColleted=true;
                            break;
                        }
                    }
                }
            }
        },2000);
        return isColleted;
    }


    /**
     * 收藏按钮的处理
     * @param itemObjecId 当前物品id
     */
    private void disposeFabLike(String itemObjecId){
        //当前在线用户的收藏数＋1||-1
        if (checkOnline()){
            //判断在线用户是否收藏了该物品
                if (checkItemCollected()){
                    //--
                    MangoApplication.mapCollectReduce();
                    //有bug
                    //更新服务器数据
                    iItemDetailsPer.deleteCollect(nowCollectId);
                    showMsg("已取消收藏");
                }else {
                    //++
                    MangoApplication.mapCollectAdd();
                    //更新服务器数据
                    iItemDetailsPer.addCollect(itemObjecId,MangoApplication.getUser().getObjectId());
                    showMsg("收藏+1");
                }
        }else {
            showMsg("你还没登录!");
        }
    }
}
