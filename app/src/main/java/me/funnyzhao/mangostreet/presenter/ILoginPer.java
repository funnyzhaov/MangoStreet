package me.funnyzhao.mangostreet.presenter;

/**
 * Created by funnyzhao .
 */

public interface ILoginPer extends BasePer{
    /**
     * 用户登录
     * @param username
     * @param password
     */
    void userLogin(String username,String password);
}
