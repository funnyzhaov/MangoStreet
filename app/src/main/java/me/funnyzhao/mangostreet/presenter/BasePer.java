package me.funnyzhao.mangostreet.presenter;

/**
 * Created by funnyzhao .
 */

public interface BasePer {
    /**
     * 显示无网络连接
     */
    void showNoNetWork();
    /**
     * 请求响应结果
     * @param info
     */
    void showRequestInfo(String info);
}
