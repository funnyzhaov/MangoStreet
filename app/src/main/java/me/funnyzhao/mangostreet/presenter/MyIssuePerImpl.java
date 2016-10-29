package me.funnyzhao.mangostreet.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.ui.activity.ItemDetailsActivity;
import me.funnyzhao.mangostreet.ui.activity.MyIssueActivity;
import me.funnyzhao.mangostreet.util.request.DeleteImage;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;
import me.funnyzhao.mangostreet.view.IMyIssueView;

/**
 * Created by funnyzhao .
 */

public class MyIssuePerImpl implements IMyIssuePer {
    private IMyIssueView iMyIssueView;

    public MyIssuePerImpl(IMyIssueView iMyIssueView){
        this.iMyIssueView=iMyIssueView;
    }

    @Override
    public void loadMyItem() {
        //加载所有
        RetrofitRequest.getAllItems(this);
    }

    @Override
    public void responseItem(List<Item> itemList) {
        List<Item> missueItem=new ArrayList<>();
        //处理数据
        for (int i=0;i<itemList.size();i++){
            if (itemList.get(i).getUserObjectId().equals(MangoApplication.getUser().getObjectId())){
                missueItem.add(itemList.get(i));
            }
        }
        if (missueItem.size()<1){
            iMyIssueView.stopLoading();
            iMyIssueView.setMyRecyclerView(null);
        }else {
            //时间排序
            List<Item> retStr=new ArrayList<Item>();
            Map<Long,Item> map = new TreeMap<Long,Item>();
            for(int i=0;i<missueItem.size();i++){
                map.put((missueItem.get(i).getCreatedAt().getTime()),missueItem.get(i));
            }
            Collection<Item> coll=map.values();
            retStr.addAll(coll);
            Collections.sort(retStr);
            iMyIssueView.stopLoading();
            iMyIssueView.setMyRecyclerView(retStr);
        }
    }

    @Override
    public void toShowDetailsPage(MyIssueActivity myIssueActivity, Item item) {
        Intent intent=new Intent(myIssueActivity, ItemDetailsActivity.class);
        intent.putExtra("item",item);
        //用户在线，所以tag为2
        intent.putExtra("tag",2);
        myIssueActivity.startActivity(intent);
    }

    @Override
    public void deleteMyItem(String objectId,String url) {
        //删除网络图片
        DeleteImage.deleteMyissue(url);
        //删除数据
        RetrofitRequest.deleteIssueById(objectId);
    }


    @Override
    public void showNoNetWork() {
        iMyIssueView.showNetWorkInfo();
    }

    @Override
    public void showRequestInfo(String info) {

    }
}
