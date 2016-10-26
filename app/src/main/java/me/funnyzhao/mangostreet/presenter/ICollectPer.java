package me.funnyzhao.mangostreet.presenter;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Collect;
import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 */

public interface ICollectPer extends BasePer {
    /**
     * 加载我的收藏数据
     */
    void loadMyCollect();

    /**
     * 响应当前用户的Collect
     * @param collects
     */
    void responseCollect(Collect[] collects);

    /**
     * 响应所有物品
     * @param itemList
     */
    void responseItem(List<Item> itemList);
}
