package me.funnyzhao.mangostreet;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import me.funnyzhao.mangostreet.bean._User;

/**
 * Created by funnyzhao .
 */

public class MangoApplication extends Application{
    //TAG
    private static final String TAG="MangoAPP";

    //User数据
    private  static _User user=new _User();
    //存储发布个数和收藏数
    private static HashMap<String,Integer> hashMap=new HashMap<>(10);

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG)
                .hideThreadInfo()
                .methodCount(3)
                .logLevel(LogLevel.NONE)
                .methodOffset(2);
    }

    public static  _User getUser() {
        return user;
    }

    public static void setUser(_User users) {
        user = users;
    }

    public static int getHashMapSize() {
        return hashMap.size();
    }

    public static void mapPut(String key,Integer value) {
        hashMap.put(key,value);
    }
    public static Integer mapGet(String key) {
        return hashMap.get(key);
    }
}
