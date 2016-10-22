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

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.ui.adapter.GoodsAdapter;

/**
 * Created by funnyzhao .
 * 推荐商品
 */

public class RecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    //刷新完成
    private static final int REFRESH_COMPLETE = 0X110;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rcv_recommend)
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
//                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recommend,container,false);
        //初始化
        unbinder= ButterKnife.bind(this,view);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        initFloatButton(view);
        setmRecyclerView();
        return view;
    }
    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
    private void setmRecyclerView(){
        List<Item> items=new ArrayList<>();
        for (int i=0;i<6;i++){
            items.add(new Item());
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        mAdapter = new GoodsAdapter(items,getActivity());
        //设置数据适配器
        mRecyclerView.setAdapter(mAdapter);
    }
    private void initFloatButton(View root){
        fab = (FloatingActionButton)root.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}

//----------------------------------------
//    setRefreshing(boolean): 显示或隐藏刷新进度条
//    isRefreshing(): 检查是否处于刷新状态