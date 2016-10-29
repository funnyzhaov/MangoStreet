package me.funnyzhao.mangostreet.presenter;

import java.util.List;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.ui.activity.MyIssueActivity;

/**
 * Created by funnyzhao .
 * 我的发布
 */

public interface IMyIssuePer extends BasePer {
    /**
     * 加载我的发布数据
     */
    void loadMyItem();

    /**
     * 响应数据
     * @param itemList
     */
    void responseItem(List<Item> itemList);


    /**
     * 进入详情页
     * @param collectActivity
     * @param item
     */
    void toShowDetailsPage(MyIssueActivity collectActivity, Item item);

    /**
     * 删除我的发布信息
     * @param objectId 物品id
     * @param url 图片网络url
     */
    void deleteMyItem(String objectId,String url);

}
