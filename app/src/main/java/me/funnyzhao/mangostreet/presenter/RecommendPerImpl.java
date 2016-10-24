package me.funnyzhao.mangostreet.presenter;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.model.IRecommendModel;
import me.funnyzhao.mangostreet.model.RecommendModelImpl;
import me.funnyzhao.mangostreet.view.IRecommendView;

/**
 * Created by funnyzhao .
 */

public class RecommendPerImpl implements IRecommendPer {

    private IRecommendView iRecommendView;
    private IRecommendModel iRecommendModel;
//    private static List<Item> oldItems;

    public RecommendPerImpl(IRecommendView iRecommendView){

        this.iRecommendView=iRecommendView;
        iRecommendModel=new RecommendModelImpl();
    }
    @Override
    public void loadItems() {
        iRecommendModel.loadItems(iRecommendModel,this);
    }

    @Override
    public void responseItems(List<Item> itemList) {
            iRecommendView.updateItems(itemList);
    }

    @Override
    public void showNoNetWork() {
        iRecommendView.showMsg("无网络连接");
    }

    @Override
    public void showRequestInfo(String info) {
        iRecommendView.showMsg(info);
    }

}
