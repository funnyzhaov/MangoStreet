package me.funnyzhao.mangostreet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.IRecommendPer;
import me.funnyzhao.mangostreet.presenter.RecommendPerImpl;
import me.funnyzhao.mangostreet.ui.adapter.GoodsAdapter;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.IRecommendView;

/**
 * Created by funnyzhao .
 * 推荐商品
 */

public class RecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IRecommendView{

    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.rcv_recommend)
    RecyclerView mRecyclerView;


    private Unbinder unbinder;
    private GoodsAdapter mAdapter;

    /**----------------------
     * Presenter
     *---------------------*/
    private IRecommendPer iRecommendPer;

    /**----------------------
     * RecyclerView数据
     *-----------------------*/
    private List<Item> itemList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recommend,container,false);
        //初始化
        unbinder= ButterKnife.bind(this,view);
        initPresenter();
        setmSwipeLayout();
        initFloatButton(view);
        setmRecyclerView();
        return view;
    }
    @Override
    public void onRefresh() {
        if (NetWorkUtil.isNetConnect(getActivity())){
            iRecommendPer.loadItems();
        }else {
            iRecommendPer.showNoNetWork();
        }
    }
    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateItems(List<Item> itemLists) {
        int count=0;
        for (Item item:itemLists){
            if (itemList.contains(item)){
                count++;
                continue;
            }else {
                count++;
                break;
            }
        }
        if (count==itemList.size()){
            mSwipeLayout.setRefreshing(false);
            showMsg("已经是最新了");
        }else {
            this.itemList.clear();
            mAdapter.notifyDataSetChanged();
            for (Item item:itemLists){
                this.itemList.add(0,item);
            }
            mAdapter.notifyItemRangeInserted(0,itemList.size());
            mRecyclerView.smoothScrollToPosition(0);
            mSwipeLayout.setRefreshing(false);
        }
    }
    /**
     * 设置刷新组件
     */
    private void setmSwipeLayout(){
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        //第一次进入时刷新
        mSwipeLayout.post(new Runnable(){
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
        this.onRefresh();
    }

    /**
     * 设置RecyclerView
     */
    private void setmRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        mAdapter = new GoodsAdapter(itemList,getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
    private void initFloatButton(View root){
        fab = (FloatingActionButton)root.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
    }
    /**
     * 初始化Presenter
     */
    private void initPresenter(){
        iRecommendPer=new RecommendPerImpl(this);
    }
}
