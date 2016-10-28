package me.funnyzhao.mangostreet.presenter;

/**
 * Created by funnyzhao .
 */

public interface IIssuePer {
    /**
     * 发布物品
     */
    void issueItem();

    /**
     * 上传图片成功后的响应
     * @param imageNetPath
     */
    void responseImagePath(String imageNetPath);

    /**
     * 上传物品成功
     */
    void responseItemSuccess();

}
