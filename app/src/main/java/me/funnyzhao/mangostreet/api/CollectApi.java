package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean.request.CollectBody;
import me.funnyzhao.mangostreet.bean.success.AddCollectBody;
import me.funnyzhao.mangostreet.bean.success.CollectResultBody;
import me.funnyzhao.mangostreet.bean.success.DeleteCollectBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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


    /**
     * 删除一行Collect数据
     * @param objectId
     * @return
     */
    @Headers({
            "X-Bmob-Application-Id:9b00125d964e776ec5b4c79c06dd6c05",
            "X-Bmob-REST-API-Key:f69e70ef1a410db0343f21ffc2ea8b7e",
            "Content-Type:application/json"
    })
    @DELETE("classes/Collect/{objectId}")
    Call<DeleteCollectBody> deleteColletById(@Path("objectId") String objectId);


    /**
     *  添加一行Collect数据
     * @param collectBody
     * @return
     */
    @POST("classes/Collect")
    Call<AddCollectBody> addCollect(@Body CollectBody collectBody);

}
