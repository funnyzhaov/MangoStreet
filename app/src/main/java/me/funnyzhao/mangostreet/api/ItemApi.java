package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean.success.ItemResultBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by funnyzhao .
 * 物品数据请求API
 */

public interface ItemApi {
    /**
     * 通过用户获取发布物品的集合
     * @param userName 发布人
     * @return
     */
    @GET("classes/Item")
    Call<ItemResultBody> getItemByuserName(@Query("userName")  String userName);
//    https://api.bmob.cn/1/classes/Item?where=%7B%22userName%22:%22funnyzhao%22%7D

}
