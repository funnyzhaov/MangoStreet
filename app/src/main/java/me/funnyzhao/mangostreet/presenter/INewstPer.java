package me.funnyzhao.mangostreet.presenter;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 */

public interface INewstPer extends BasePer {
    /**
     * 加载数据
     */
    void loadItems();

    /**
     * 响应请求数据
     * @param itemList
     */
    void responseItems(List<Item> itemList);

}
