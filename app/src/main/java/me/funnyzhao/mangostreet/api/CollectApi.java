package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean.success.CollectResultBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by funnyzhao .
 * 收藏数据请求API
 */

public interface CollectApi {
    /**
     * 通过用户获取收藏信息的集合
     * @param userName 发布人
     * @return
     */
    @Headers({
            "X-Bmob-Application-Id:9b00125d964e776ec5b4c79c06dd6c05",
            "X-Bmob-REST-API-Key:f69e70ef1a410db0343f21ffc2ea8b7e",
            "Content-Type:application/json"
    })
    @GET("classes/Collect")
    Call<CollectResultBody> getCollectByuserName(@Query("userName") String userName);
}
