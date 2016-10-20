package me.funnyzhao.mangostreet.view;

import me.funnyzhao.mangostreet.bean._User;

/**
 * Created by funnyzhao .
 */

public interface IUserInfoView {
    /**
     * 设置收藏和发布数据
     */
    void setCollectAndReseled();

    /**
     * 设置用户相关数据
     */
    void setUserData(_User userData);

    /**
     * 显示CardView
     */
    void showCardViews();

    /**
     * 隐藏动画加载条
     */
    void stopProgress();

    /**
     * 加载图片
     */
    void loadImage();

    /**
     * 加载图片  onStart
     */
    void onLoadDefaultImage();
}
