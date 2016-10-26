package me.funnyzhao.mangostreet.presenter.disposeitem;

import me.funnyzhao.mangostreet.bean.Collect;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.BasePer;

/**
 * Created by funnyzhao .
 * 物品详情
 */

public interface IItemDetailsPer extends BasePer {
//-----------物品详情----------------------
    /**
     * 加载发布人信息
     */
    void loadUserInfo();
    /**
     * 请求成功
     * @param user
     */
    void loadSuccess(_User user);

    /**
     * 请求成功
     */
    void loadFailure();
//-------------------------------------

    /**
     * 获取所有收藏物品的信息
     */
    void loadCollectAll();

    /**
     * 响应
     * @param collects
     */
    void responCollects(Collect[] collects);

//-----------------------------------------------
    //like

    /**
     * 删除一行收藏数据
     * @param objectId
     */
    void deleteCollect(String objectId);
    /**
     * 添加一行收藏数据
     * @param objectId
     * @param userObjectId
     */
    void addCollect(String objectId,String userObjectId);

}
