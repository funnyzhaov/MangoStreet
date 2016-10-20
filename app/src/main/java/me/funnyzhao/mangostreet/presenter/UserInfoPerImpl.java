package me.funnyzhao.mangostreet.presenter;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.model.IUserInfoModel;
import me.funnyzhao.mangostreet.model.UserInfoModelImpl;
import me.funnyzhao.mangostreet.view.IUserInfoView;

/**
 * Created by funnyzhao .
 */

public class UserInfoPerImpl implements IUserInfoPer {
    IUserInfoView iUserInfoView;
    IUserInfoModel iUserInfoModel;
    public UserInfoPerImpl(IUserInfoView iUserInfoView){
        this.iUserInfoView=iUserInfoView;
        iUserInfoModel=new UserInfoModelImpl();
    }


    @Override
    public void checkLocalData() {
        //检查本地是否存在数据
        if (iUserInfoModel.isLocalData()){
            iUserInfoView.stopProgress();
            iUserInfoView.showCardViews();
            iUserInfoView.setCollectAndReseled();
            iUserInfoView.setUserData(MangoApplication.getUser());
            if (MangoApplication.getUser().getImageurl()==null||MangoApplication.getUser().getImageurl().equals("")){
               iUserInfoView.onLoadDefaultImage();
            }else {
                iUserInfoView.loadImage();
            }
        }else {
            //如果本地不存在，则从服务器请求
            iUserInfoModel.isGetNumOk(this);
        }
    }

    @Override
    public void getNetDataSuccess() {
        iUserInfoView.stopProgress();
        iUserInfoView.showCardViews();
        iUserInfoView.setCollectAndReseled();
        iUserInfoView.setUserData(MangoApplication.getUser());
        iUserInfoView.loadImage();
    }

}
