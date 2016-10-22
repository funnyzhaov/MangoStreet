package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean.success.ItemResultBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by funnyzhao .
 * 物品数据请求API
 */

public interface ItemApi {
    /**
     * 通过用户获取发布物品的集合 || 获取所有物品集合
     * @return
     */
    @GET("classes/Item")
    Call<ItemResultBody> getItemByuserName();

}
