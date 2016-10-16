package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.presenter.IRegisterPer;

/**
 * Created by funnyzhao .
 */

public interface IRegisterModel {
    /**
     * 注册用户
     * @param registerPer 控制层
     * @param username
     * @param password
     */
    void registerUser(IRegisterPer registerPer, String username, String password);
}
