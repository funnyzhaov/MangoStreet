package me.funnyzhao.mangostreet.view;

import me.funnyzhao.mangostreet.bean._User;

/**
 * Created by funnyzhao .
 */

public interface IEditorUserView {
    /**
     * 显示提示对话框(询问用户是否需要更改头像)
     */
    void showPhotoHint();

    /**
     * 获取控件新的数据
     */
    _User getNewData();

    /**
     * 设置图片上传后的的url
     * @param imageUrl
     */
    void setImageUrl(String imageUrl);

    /**
     * 显示上传动画
     */
    void showProgress();

    /**
     * 关闭上传动画
     */
    void stopProgress();

    /**
     * 显示保存信息动画
     */
    void showDoneProgress();

    /**
     * 关闭保存信息动画
     */
    void stopDoneProgress();

    /**
     * 显示提示消息
     * @param msg
     */
    void showToastHint(String msg);

    /**
     * 返回上一个页面
     */
    void backUserActivity();

}
