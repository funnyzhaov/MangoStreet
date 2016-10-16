package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.presenter.IUserInfoPer;

/**
 * Created by funnyzhao .
 */

public interface IUserInfoModel {
    /**
     * 获取发布和收藏数据
     * @return
     */
    void isGetNumOk(IUserInfoPer iUserInfoPer);

    /**
     * 检查本地是否有数据
     * @return
     */
    boolean isLocalData();
}
