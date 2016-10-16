package me.funnyzhao.mangostreet.view;

/**
 * Created by funnyzhao .
 * RegisterActivity的UI操作
 */

public interface IRegisterView extends BaseView{
    /**
     * 获取邮箱
     * @return
     */
    String getUsername();

    /**
     * 获取密码
     * @return
     */
    String getPassword();

    /**
     * 显示注册成功提示
     */
    void showSuccesInfo();

    /**
     * 显示注册失败提示
     */
    void showFailureInfo();

    /**
     * 显示请求失败
     */
    void showRequestFailureInfo();
    /**
     * 清空输入框内信息
     */
    void clearEditText();
}
