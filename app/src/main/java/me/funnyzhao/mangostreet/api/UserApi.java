package me.funnyzhao.mangostreet.api;

import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.bean.request.RegisterBody;
import me.funnyzhao.mangostreet.bean.success.FilesResultBody;
import me.funnyzhao.mangostreet.bean.success.SuccessBody;
import me.funnyzhao.mangostreet.bean.success.UpdateUserBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by funnyzhao .
 */

public interface UserApi {

    /**
     * 用户注册
     *
     * @param registerBody 注册信息请求体
     * @return
     */
    @POST("users")
    Call<SuccessBody> registeredUser(@Body RegisterBody registerBody);

    /**
     * 用户登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GET("login")
    Call<_User> checkUser(@Query("username") String username,
                          @Query("password") String password);

    /**
     * 根据用户id更新用户信息
     * @param objectId
     * @return
     */
    @PUT("users/{objectId}")
    Call<UpdateUserBody> updateUserInfo(@Header("X-Bmob-Session-Token") String SessionToken,
                                        @Path("objectId") String objectId, @Body _User user);


    /**
     * 上传用户头像
     * @return
     */
    @Headers({
            "X-Bmob-Application-Id:9b00125d964e776ec5b4c79c06dd6c05",
            "X-Bmob-REST-API-Key:f69e70ef1a410db0343f21ffc2ea8b7e",
            "Content-Type:image/png"
    })
    @Multipart
    @POST("files/{image}")
    Call<FilesResultBody> uploadImageFile(@Path("image") String name , @Part MultipartBody.Part file
            , @Part("description") RequestBody requestBody);
}