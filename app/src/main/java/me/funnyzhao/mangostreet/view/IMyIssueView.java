package me.funnyzhao.mangostreet.view;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 */

public interface IMyIssueView extends BaseView{
    /**
     * 设置标题
     * @param itemList
     */
    void setMyRecyclerView(List<Item> itemList);

    /**
     * 关闭加载动画
     */
    void stopLoading();
}
