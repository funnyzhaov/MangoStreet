package me.funnyzhao.mangostreet.view;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 */

public interface IRecommendView {
    /**
     * 显示消息
     * @param msg
     */
    void showMsg(String msg);

    /**
     * 更新list
     * @param itemList
     */
    void updateItems(List<Item> itemList);
}
