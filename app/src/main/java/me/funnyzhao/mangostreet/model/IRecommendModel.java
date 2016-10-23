package me.funnyzhao.mangostreet.model;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.IRecommendPer;

/**
 * Created by funnyzhao .
 *  推荐
 */

public interface IRecommendModel {
    /**
     * 加载推荐数据
     * @param iRecommendModel
     * @param iRecommendPer
     */
    void loadItems(IRecommendModel iRecommendModel, IRecommendPer iRecommendPer);

    /***
     * 响应数据
     * @param itemList
     */
    void responseItems(List<Item> itemList);
}
