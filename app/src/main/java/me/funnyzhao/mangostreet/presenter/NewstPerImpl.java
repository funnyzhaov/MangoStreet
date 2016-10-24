package me.funnyzhao.mangostreet.presenter;

import java.util.List;

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
        iNewstView.updateItems(itemList);
    }
}
