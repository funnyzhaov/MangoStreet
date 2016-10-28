package me.funnyzhao.mangostreet.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.bean.Collect;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.ui.activity.ItemDetailsActivity;
import me.funnyzhao.mangostreet.ui.activity.MyCollectActivity;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;
import me.funnyzhao.mangostreet.view.ICollectView;

/**
 * Created by funnyzhao .
 */

public class CollectPerImpl implements ICollectPer {
    private ICollectView iCollectView;
    private Set<String> collectItemid=new HashSet<>();
    public CollectPerImpl(ICollectView iCollectView){
        this.iCollectView=iCollectView;
    }
    @Override
    public void showNoNetWork() {
        iCollectView.showNetWorkInfo();
    }

    @Override
    public void showRequestInfo(String info) {
    }

    @Override
    public void loadMyCollect() {
        //加载所有
        RetrofitRequest.getAllCollect(this);
    }

    @Override
    public void responseCollect(Collect[] collects) {
        if (collects!=null){
            //比对userid==当前用户id，取出数据
            for (int i=0;i<collects.length;i++){
                if (collects[i].getUserObjectId().equals(MangoApplication.getUser().getObjectId())){
                    collectItemid.add(collects[i].getItemId());
                }
            }
            if (collectItemid.size()==0){
                iCollectView.setMyRecyclerView(null);
            }else {
                loadAllItems();
            }
        }else {
            iCollectView.setMyRecyclerView(null);
        }
    }

    @Override
    public void responseItem(List<Item> itemList) {
        //再查询所有的Item，比对当前数据的itemId，获取用户的收藏Item
        List<Item> newItemList=new ArrayList<>();
        for (int i=0;i<itemList.size();i++){
            for (int j=0;j<collectItemid.size();j++){
                if (collectItemid.contains(itemList.get(i).getObjectId())){
                    newItemList.add(itemList.get(i));
                }
            }
        }
        newItemList=new ArrayList(new HashSet(newItemList));
        iCollectView.stopLoading();
        iCollectView.setMyRecyclerView(newItemList);
    }

    @Override
    public void toShowDetailsPage(MyCollectActivity collectActivity, Item item) {
        Intent intent=new Intent(collectActivity, ItemDetailsActivity.class);
        intent.putExtra("item",item);
        //用户在线，所以tag为2
        intent.putExtra("tag",2);
        collectActivity.startActivity(intent);
    }

    /**
     * 加载所有Item,响应数据后比对
     */
    private void loadAllItems(){
        RetrofitRequest.getAllItems(this);
    }

}
