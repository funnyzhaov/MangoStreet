package me.funnyzhao.mangostreet.presenter;

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
    public boolean getOnlineNum() {
        return iUserInfoModel.isGetNumOk()?true:false;
    }
}
