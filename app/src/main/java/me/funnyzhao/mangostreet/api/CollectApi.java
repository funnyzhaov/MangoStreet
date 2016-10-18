package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean.success.CollectResultBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by funnyzhao .
 * 收藏数据请求API
 */

public interface CollectApi {
    /**
     * 通过用户获取收藏信息的集合
     * @return
     */
    @GET("classes/Collect")
    Call<CollectResultBody> getCollectByuserName();
}
