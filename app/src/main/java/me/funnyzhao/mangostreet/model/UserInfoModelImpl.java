package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.presenter.IUserInfoPer;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 */

public class UserInfoModelImpl implements IUserInfoModel {
    @Override
    public void isGetNumOk(IUserInfoPer iUserInfoPer) {
        String userNme=MangoApplication.getUser().getUsername();
         RetrofitRequest.toGetItemsOnline(userNme);
         RetrofitRequest.toGetCollectOnline(userNme,iUserInfoPer);
    }

    @Override
    public boolean isLocalData() {
        if (MangoApplication.getHashMapSize()!=0){
            return true;
        }else {
            return false;
        }
    }
}
