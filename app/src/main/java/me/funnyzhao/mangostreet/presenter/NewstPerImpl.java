package me.funnyzhao.mangostreet.presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.model.INewstModel;
import me.funnyzhao.mangostreet.model.NewstModelImpl;
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
        iNewstView.updateItems(sortByTime(itemList));
    }

    private List<Item> sortByTime(List<Item> itemsList){
        List<Item> retStr=new ArrayList<Item>();
        Map<Long,Item> map = new TreeMap<Long,Item>();
        for(int i=0;i<itemsList.size();i++){
            map.put((itemsList.get(i).getCreatedAt().getTime()),itemsList.get(i));
        }
        Collection<Item> coll=map.values();
        retStr.addAll(coll);
        Collections.reverse(retStr);
        return retStr;
    }
}
