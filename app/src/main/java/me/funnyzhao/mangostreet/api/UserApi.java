package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.bean.request.RegisterBody;
import me.funnyzhao.mangostreet.bean.success.SuccessBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("users")
    Call<SuccessBody> registeredUser(@Body RegisterBody registerBody);

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password  密码
     * @return
     */
    @GET("login")
    Call<_User> checkUser(@Query("username") String username,
                          @Query("password") String password);

}
