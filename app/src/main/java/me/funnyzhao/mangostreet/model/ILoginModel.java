package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.presenter.ControlUser;
import me.funnyzhao.mangostreet.presenter.ILoginPer;

/**
 * Created by funnyzhao .
 * 登录业务
 */

public interface ILoginModel {
    /**
     * 用户登录验证
     * @param iLoginPer
     * @param username
     * @param password
     */
    void userLogin(ILoginPer iLoginPer, String username, String password, ControlUser controlUserPer);
}
