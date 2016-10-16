package me.funnyzhao.mangostreet.view;

/**
 * Created by funnyzhao .
 * HomeActivity的UI操作
 */

public interface IHomeView extends BaseView {
    /**
     * 初始化Toolbar
     */
    void initToolbar();

    /**
     *初始化侧滑布局
     */
    void initDrawerLayout();

    /**
     * 用户是否在线
     * @return true:显示在线控件？显示提示登录控件
     */
    boolean isOnline();

    /**
     * 显示在线控件
     */
    void showUserWidget();

    /**
     * 显示提示登录控件
     */
    void showHintWidget();
    /**
     * 显示响应消息
     * @param msg
     */
    void showResponseMsg(String msg);
}
