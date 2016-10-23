package me.funnyzhao.mangostreet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.IRecommendPer;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 */

public class RecommendModelImpl implements IRecommendModel {
    private IRecommendPer iRecommendPer;
    private List<Item> mItemList=new ArrayList<>();
    public RecommendModelImpl(IRecommendPer iRecommendPer){
        this.iRecommendPer=iRecommendPer;
    }
    @Override
    public void loadItems(IRecommendModel iRecommendModel,IRecommendPer iRecommendPer) {
        RetrofitRequest.getRecommendItems(iRecommendModel,iRecommendPer);
    }

    @Override
    public void responseItems(List<Item> itemList) {
        sortByCollectnum(itemList);
        iRecommendPer.responseItems(mItemList);
    }

    /**
     * 根据收藏数排序  <=6
     * @param items
     */
    private void sortByCollectnum(List<Item> items){
        int i=0;
        //先取出收藏数>2的
        for (int j=0;j<items.size();j++) {
            if (i<=j){
                if (i<6){
                    if (Integer.valueOf(items.get(j).getCollectNum())>=2){
                        mItemList.add(items.get(j));
                        i++;
                    }
                }else {
                    break;
                }
            }
        }
        //将序排列
        Collections.sort(mItemList);
    }
}
