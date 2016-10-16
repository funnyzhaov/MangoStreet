package me.funnyzhao.mangostreet.view;

/**
 * Created by funnyzhao .
 * 登录页面UI操作
 */

public interface ILoginView extends BaseView{
    /**
     * 获取用户名
     * @return
     */
    String getUserName();

    /**
     * 获取密码
     * @return
     */
    String getPassword();

    /**
     * 清空输入框信息
     */
    void clearEditText();

    /**
     * 显示响应消息
     * @param msg
     */
    void showResponseMsg(String msg);
}
