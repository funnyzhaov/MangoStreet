package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.bean.request.RegisterBody;
import me.funnyzhao.mangostreet.bean.success.SuccessBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by funnyzhao .
 */

public interface UserApi {

    /**
     * 用户注册
     * @param registerBody 注册信息请求体
     * @return
     */
    @Headers({
            "X-Bmob-Application-Id:9b00125d964e776ec5b4c79c06dd6c05",
            "X-Bmob-REST-API-Key:f69e70ef1a410db0343f21ffc2ea8b7e",
            "Content-Type:application/json"
    })
    @POST("users")
    Call<SuccessBody> registeredUser(@Body RegisterBody registerBody);

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password  密码
     * @return
     */
    @Headers({
            "X-Bmob-Application-Id:9b00125d964e776ec5b4c79c06dd6c05",
            "X-Bmob-REST-API-Key:f69e70ef1a410db0343f21ffc2ea8b7e",
            "Content-Type:application/json"
    })
    @GET("login")
    Call<_User> checkUser(@Query("username") String username,
                          @Query("password") String password);

}
