package me.funnyzhao.mangostreet.presenter;

/**
 * Created by funnyzhao .
 * 注册页面控制层
 */

public interface IRegisterPer extends BasePer{
    /**
     * 注册用户
     * @param username
     * @param password
     */
    void toRegisterUser(String username,String password);
}
