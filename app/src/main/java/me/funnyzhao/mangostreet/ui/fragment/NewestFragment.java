package me.funnyzhao.mangostreet.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import me.funnyzhao.mangostreet.presenter.INewstPer;
import me.funnyzhao.mangostreet.presenter.NewstPerImpl;
import me.funnyzhao.mangostreet.ui.adapter.GoodsAdapter;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.INewstView;

/**
 * Created by funnyzhao .
 * 最新发布
 */

public class NewestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,INewstView{
    //刷新完成
    private static final int REFRESH_COMPLETE = 0X110;

    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rcv_newest)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;
    private GoodsAdapter mAdapter;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };

    /**----------------------
     * Presenter
     *---------------------*/
    private INewstPer iNewstPer;

    /**----------------------
     * RecyclerView数据
     *-----------------------*/
    private List<Item> itemList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_newest,container,false);
        //初始化
        unbinder= ButterKnife.bind(this,view);
        initPresenter();
        setmSwipeLayout();
        initFloatButton(view);
        setmRecyclerView();
        return view;
    }

    /**
     * 刷新逻辑
     */
    @Override
    public void onRefresh() {
        if (NetWorkUtil.isNetConnect(getActivity())){
            iNewstPer.loadItems();
        }else {
            iNewstPer.showNoNetWork();
        }
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
    private void setmSwipeLayout(){
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
    }
    private void setmRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        mAdapter = new GoodsAdapter(itemList,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        //检查网络连接
        if (NetWorkUtil.isNetConnect(getActivity())){
            iNewstPer.loadItems();
            //设置
        }else {
            iNewstPer.showNoNetWork();
        }
    }

    private void initFloatButton(View root){
        fab = (FloatingActionButton)root.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter(){
        iNewstPer=new NewstPerImpl(this);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateItems(List<Item> itemList) {
        for (Item item:itemList){
            this.itemList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }
}

//----------------------------------------
//    setRefreshing(boolean): 显示或隐藏刷新进度条
//    isRefreshing(): 检查是否处于刷新状态