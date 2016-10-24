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

    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rcv_newest)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;
    private GoodsAdapter mAdapter;

    /**----------------------
     * Presenter
     *---------------------*/
    private INewstPer iNewstPer;

    /**----------------------
     * RecyclerView数据
     *-----------------------*/
    private static List<Item> itemList=new ArrayList<>();

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
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
    private void setmSwipeLayout(){
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeLayout.post(new Runnable(){
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
        this.onRefresh();
    }
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
        iNewstPer=new NewstPerImpl(this);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    /*
     *下拉刷新逻辑：
     * 判断缓存的list1中是否包含list2中的元素，如果都包含，那么表示服务器无最新数据
     * 如果有一个不包含，表示服务器有了数据的改变或者有了新数据，则更新当前list1.
     * __created by funnyzhao.
     */
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
            itemList.clear();
            mAdapter.notifyDataSetChanged();
            for (int i=itemLists.size()-1;i>=0;i--){
                itemList.add(0,itemLists.get(i));
            }
            mAdapter.notifyItemRangeInserted(0,itemList.size());
            mRecyclerView.smoothScrollToPosition(0);
            mSwipeLayout.setRefreshing(false);
        }

    }
}
