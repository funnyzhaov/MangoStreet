package me.funnyzhao.mangostreet.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.model.INewstModel;
import me.funnyzhao.mangostreet.model.NewstModelImpl;
import me.funnyzhao.mangostreet.ui.activity.ItemDetailsActivity;
import me.funnyzhao.mangostreet.view.INewstView;

/**
 * Created by funnyzhao .
 */

public class NewstPerImpl implements INewstPer {
    private INewstModel iNewstModel;
    private INewstView iNewstView;
    public NewstPerImpl(INewstView iNewstView){
        this.iNewstView=iNewstView;
        iNewstModel=new NewstModelImpl();
    }

    //Base
    @Override
    public void showNoNetWork() {
        iNewstView.showMsg("无网络连接");
    }

    @Override
    public void showRequestInfo(String info) {
        iNewstView.showMsg(info);
    }

    @Override
    public void loadItems() {
        iNewstModel.loadItemsNet(this);
    }

    @Override
    public void responseItems(List<Item> itemList) {
        List<Item> updateList=sortByTime(itemList);
        iNewstView.updateItems(updateList);

    }

    @Override
    public void toShowDetailsPage(Context context, Item item) {
        Intent intent=new Intent(context, ItemDetailsActivity.class);
        intent.putExtra("item",item);
        context.startActivity(intent);
    }
    private List<Item> sortByTime(List<Item> itemsList){
        List<Item> items=new ArrayList<>();
        //数据截取
        for (Item item:itemsList){
            if (item.getItemDescription().length()>10){
                item.setItemSubDescription(item.getItemDescription().substring(0,10)+"...");
            }else{
                item.setItemSubDescription(item.getItemDescription());
            }
            items.add(item);
        }
        //排序
        List<Item> retStr=new ArrayList<Item>();
        Map<Long,Item> map = new TreeMap<Long,Item>();
        for(int i=0;i<items.size();i++){
            map.put((items.get(i).getCreatedAt().getTime()),items.get(i));
        }
        Collection<Item> coll=map.values();
        retStr.addAll(coll);
        Collections.reverse(retStr);
        return retStr;
    }
}
