package me.funnyzhao.mangostreet.util.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.io.File;

import me.funnyzhao.mangostreet.api.UserApi;
import me.funnyzhao.mangostreet.bean.success.FilesResultBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by funnyzhao .
 */

public class FileRequest {
    private static Gson gson;
    private  static Retrofit retrofit;

    //初始化gson、retrofit
    private static void init() {
        gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bmob.cn/2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    /**
     * 上传用户头像
     */
    public static void toUploadUserimage(String userName,String filePath){
        init();
        File file = new File(filePath);

        UserApi api=retrofit.create(UserApi.class);
        Logger.d(file.toString()+"  "+filePath+"   "+file.getName());
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", userName+".png", photoRequestBody);
        String descriptionString = "hello, this is description ";
        RequestBody description = RequestBody.create(null, descriptionString);
        Call<FilesResultBody> call=api.uploadImageFile(userName+".png",body,description);

        call.enqueue(new Callback<FilesResultBody>() {
            @Override
            public void onResponse(Call<FilesResultBody> call, Response<FilesResultBody> response) {
                if (response.isSuccessful()){
                    Logger.d("上传成功");
                    Logger.d(response.body());
                }else {
                    Logger.d(response.code());
                }
            }

            @Override
            public void onFailure(Call<FilesResultBody> call, Throwable t) {
                Logger.d("请求失败");
            }
        });
    }
}
