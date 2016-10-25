package me.funnyzhao.mangostreet.presenter.disposeitem;

import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.BasePer;

/**
 * Created by funnyzhao .
 * 物品详情
 */

public interface IItemDetailsPer extends BasePer {
    /**
     * 加载发布人信息
     */
    void loadUserInfo();

    /**
     * 请求成功
     * @param user
     */
    void loadSuccess(_User user);

    void loadFailure();

}
