package me.funnyzhao.mangostreet.view.dmview;

import me.funnyzhao.mangostreet.bean.Collect;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.view.BaseView;

/**
 * Created by funnyzhao .
 */

public interface IItemDetailsView extends BaseView {
    /**
     * 获取Item
     * @return
     */
    Item getItem();

    /**
     * 设置Item组件的信息
     */
    void setItemInfo();

    /**
     * 设置发布人数据
     * @param userInfo
     */
    void setUserInfo(_User userInfo);

    /**
     * 显示消息
     * @param msg
     */
    void showMsg(String msg);

    /**
     * 更新当前Collect
     * @param collects
     */
    void responseCollects(Collect[] collects);

    /**
     * 更新id
     */
    void setNowCollectId();

    void setFabLiked();
}

