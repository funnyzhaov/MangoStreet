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
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.IMyIssuePer;
import me.funnyzhao.mangostreet.presenter.MyIssuePerImpl;
import me.funnyzhao.mangostreet.ui.adapter.IssueGoodsAdapter;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.IMyIssueView;

/**
 * Created by funnyzhao .
 * 我的发布页面
 */

public class MyIssueActivity extends BaseActivity implements IMyIssueView{
    @BindView(R.id.issue_iv_back)
    ImageView ivBack;
    @BindView(R.id.issue_recView)
    RecyclerView mRecyclerView;
    @BindView(R.id.issue_mv_loading)
    MetroLoadingView metroLoadingView;
    private IssueGoodsAdapter mAdapter;
    private List<Item> issueItemList=new ArrayList<>();

    private IMyIssuePer iMyIssuePer;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_my_issue);
    }

    @Override
    protected void initEvents() {
        setIvBack();
        initRecyclerView();
        metroLoadingView.start();
        iMyIssuePer.loadMyItem();
    }

    @Override
    protected void initDataOrPresenter() {
        iMyIssuePer=new MyIssuePerImpl(this);
    }


    private void setIvBack(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issueItemList.clear();
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
            issueItemList.clear();
            mAdapter.notifyDataSetChanged();
            for (int i=0;i<itemList.size();i++){
                issueItemList.add(0,itemList.get(i));
            }
            mAdapter.notifyItemRangeInserted(0,itemList.size());
            mRecyclerView.smoothScrollToPosition(0);
        }else {
            //没有发布
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
        mAdapter = new IssueGoodsAdapter(issueItemList,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new IssueGoodsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //根据位置获取数据,，进入物品详情页
                if (NetWorkUtil.isNetConnect(MyIssueActivity.this)){
                    //进入详情
                    iMyIssuePer.toShowDetailsPage(MyIssueActivity.this,issueItemList.get(position));
                }else {
                    iMyIssuePer.showNoNetWork();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //长按删除
                showDialog(issueItemList.get(position).getItemName(),
                        position,
                        issueItemList.get(position).getObjectId(),issueItemList.get(position).getItemImage());
            }
        });
    }

    /**
     * 显示操作对话框
     * @param itemTitle
     * @param position
     * @param objectId 当前物品Id
     * @param url 图片url
     */
    private void showDialog(String itemTitle, final int position, final String objectId, final String url){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确认删除这条发布吗?")
                .setContentText(itemTitle)
                .setConfirmText("确认删除")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //确认删除后,Adapter移除当前信息
                        issueItemList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        mRecyclerView.smoothScrollToPosition(0);
                        //执行后台服务器删除数据操作
                        iMyIssuePer.deleteMyItem(objectId,url);
                                sDialog.setTitleText("Deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        MangoApplication.setRefreshTag(1);
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                })
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }).show();
    }
}
