package me.funnyzhao.mangostreet;

import android.app.Application;

import com.orhanobut.logger.Logger;

import java.util.HashMap;

import cn.bmob.v3.Bmob;
import me.funnyzhao.mangostreet.bean._User;

/**
 * Created by funnyzhao .
 */

public class MangoApplication extends Application{
    //TAG
    private static final String TAG="MangoAPP";

    //User数据
    private  static _User user=new _User();

    //发布后刷新的Tag，默认为0不刷新
    private static int refreshTag=0;

    public static int getRefreshTag() {
        return refreshTag;
    }

    public static void setRefreshTag(int refreshTag) {
        MangoApplication.refreshTag = refreshTag;
    }

    /**
     *  存储发布个数和收藏数
     *  在应用运行期间，如果用户发布物品或者收藏了物品信息，则更新这个map :add
     *               如果用户取消了收藏或者删除了发布信息，则更新这个map:reduce
     */
    private static HashMap<String,Integer> hashMap=new HashMap<>();
    @Override
    public void onCreate() {
        Bmob.initialize(this, "9b00125d964e776ec5b4c79c06dd6c05");
        Logger.init(TAG);
        Logger.d("Hello");
        super.onCreate();

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

    /**
     * 增加发布数
     */
    public static void mapReseledAdd(){
        int oldCount=hashMap.get("releasedCount");
        hashMap.put("releasedCount",++oldCount);
    }

    /**
     * 减少发布数(单个或者批量操作)
     * @param count
     */
    public static void mapReseledReduce(int count){
        int newCount;
        int oldCount=hashMap.get("releasedCount");
        newCount=oldCount-count;
        hashMap.put("releasedCount",newCount);
    }
    /**
     * 增加收藏数
     */
    public static void mapCollectAdd(){
        int oldCount=hashMap.get("collectCount");
        hashMap.put("collectCount",++oldCount);
    }

    /**
     * 减少收藏数(单个减少)
     */
    public static void mapCollectReduce(){
        int newCount;
        int oldCount=hashMap.get("collectCount");
        newCount=--oldCount;
        hashMap.put("collectCount",newCount);
    }

    /**
     * 放入键值对
     * @param key
     * @param value
     */
    public static void mapPut(String key,Integer value) {
        hashMap.put(key,value);
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Integer mapGet(String key) {
        return hashMap.get(key);
    }

    public static void clearMap(){
        hashMap.clear();
    }

}
