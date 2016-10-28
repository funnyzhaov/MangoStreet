package me.funnyzhao.mangostreet.view;

import com.kaopiz.kprogresshud.KProgressHUD;

import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 */

public interface IIssueView extends BaseView {
    /**
     * 显示提示信息
     * @param msg
     */
    void showMsg(String msg);

    /**
     * 获取用户选择的图片路径
     * @return
     */
    String getChoosePhoto();
    /**
     * 获取Item控件的数据
     */
    Item getItemInfo();


    //---------------------HUD
    KProgressHUD getImageHUD();

    KProgressHUD getItemHUD();
}
