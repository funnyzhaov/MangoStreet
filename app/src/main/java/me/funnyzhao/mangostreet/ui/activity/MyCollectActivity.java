package me.funnyzhao.mangostreet.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mmga.metroloading.MetroLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.CollectPerImpl;
import me.funnyzhao.mangostreet.presenter.ICollectPer;
import me.funnyzhao.mangostreet.ui.adapter.CollectGoodsAdapter;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.ICollectView;

/**
 * Created by funnyzhao .
 * 我的收藏页面
 */

public class MyCollectActivity extends BaseActivity implements ICollectView{
    @BindView(R.id.collect_iv_back)
    ImageView ivBack;
    @BindView(R.id.collect_recView)
    RecyclerView mRecyclerView;
    @BindView(R.id.collect_mv_loading)
    MetroLoadingView metroLoadingView;
    private CollectGoodsAdapter mAdapter;
    private List<Item> collectItemList=new ArrayList<>();

    private ICollectPer iCollectPer;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_my_collect);
    }

    @Override
    protected void initEvents() {
        setIvBack();
        initRecyclerView();
        metroLoadingView.start();
        iCollectPer.loadMyCollect();
    }

    @Override
    protected void initDataOrPresenter() {
        iCollectPer=new CollectPerImpl(this);

    }


    private void setIvBack(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void showNetWorkInfo() {
        Toast.makeText(this,"无网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMyRecyclerView(List<Item> itemList) {
        if (itemList!=null){
            collectItemList.clear();
            mAdapter.notifyDataSetChanged();
            for (int i=0;i<itemList.size();i++){
                collectItemList.add(0,itemList.get(i));
            }
            mAdapter.notifyItemRangeInserted(0,itemList.size());
            mRecyclerView.smoothScrollToPosition(0);
        }else {
            //没有收藏
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void stopLoading() {
        metroLoadingView.stop();
        metroLoadingView.setVisibility(View.GONE);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectGoodsAdapter(collectItemList,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CollectGoodsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //根据位置获取数据,，进入物品详情页
                if (NetWorkUtil.isNetConnect(MyCollectActivity.this)){
                    //进入详情
                    iCollectPer.toShowDetailsPage(MyCollectActivity.this,collectItemList.get(position));
                }else {
                    iCollectPer.showNoNetWork();
                }
            }
        });
    }
}
