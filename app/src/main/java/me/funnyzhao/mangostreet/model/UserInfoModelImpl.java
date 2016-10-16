package me.funnyzhao.mangostreet.model;

import com.orhanobut.logger.Logger;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 */

public class UserInfoModelImpl implements IUserInfoModel {
    @Override
    public boolean isGetNumOk() {
        String userNme=MangoApplication.getUser().getUsername();
        boolean bvalue;
        //如果map大小为0(本地没有数据，则从网络加载)
        if (MangoApplication.getHashMapSize()!=0){
            bvalue=true;
            return bvalue;
        }else{
            boolean itemOk= RetrofitRequest.toGetItemsOnline(userNme);
            boolean collectOk=RetrofitRequest.toGetCollectOnline(userNme);

            if(itemOk && collectOk){
                Logger.d("获取用户发布和收藏数据成功");
                bvalue=true;
                return bvalue;
            }else {
                Logger.d("获取用户发布和收藏数据失败");
                bvalue=false;
                return bvalue;
            }
        }
    }
}
