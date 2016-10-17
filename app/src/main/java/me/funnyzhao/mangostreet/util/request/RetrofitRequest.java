package me.funnyzhao.mangostreet.util.request;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.api.CollectApi;
import me.funnyzhao.mangostreet.api.ItemApi;
import me.funnyzhao.mangostreet.api.UserApi;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.bean.request.RegisterBody;
import me.funnyzhao.mangostreet.bean.success.CollectResultBody;
import me.funnyzhao.mangostreet.bean.success.ItemResultBody;
import me.funnyzhao.mangostreet.bean.success.SuccessBody;
import me.funnyzhao.mangostreet.presenter.ControlUser;
import me.funnyzhao.mangostreet.presenter.ILoginPer;
import me.funnyzhao.mangostreet.presenter.IRegisterPer;
import me.funnyzhao.mangostreet.presenter.IUserInfoPer;
import me.funnyzhao.mangostreet.util.Interceptor.MangoInterceptors;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by funnyzhao .
 * 网络请求工具类
 */

public  class RetrofitRequest {

    private static Gson gson;
    private  static  Retrofit retrofit;
    private static HashMap<String,Object> hashMap=new HashMap<>();

    static Handler handle=new Handler(Looper.getMainLooper());
    //初始化gson、retrofit
    private static void init() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new MangoInterceptors())
                .build();
        gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
       retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bmob.cn/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

    }

    /**
     * 用户登录验证
     * @param iLoginPer
     * @param user 用户实体
     */
   public static void toCheckLogin(final ILoginPer iLoginPer, _User user, final ControlUser controlUser){
        init();

        UserApi api=retrofit.create(UserApi.class);
        Call<_User> call=api.checkUser(user.getUsername(),user.getPassword());
        call.enqueue(new Callback<_User>() {
            @Override
            public void onResponse(Call<_User> call, Response<_User> response) {

                if(!response.isSuccessful()){
                    iLoginPer.showRequestInfo("用户名或密码错误!");
                }else{
                    //验证用户成功,返回数据给控制层
                    HashMap<String,String> hashMap=new HashMap<String, String>();
                    hashMap.put("username",response.body().getUsername());
                    hashMap.put("objectId",response.body().getObjectId());
                    Logger.d(response.body().toString());
                    controlUser.setOnlineData(hashMap);
                    controlUser.setOnlineUser(response.body());
                    //设置全局的用户信息
                    MangoApplication.setUser(response.body());
                    iLoginPer.showRequestInfo("登录成功!");
                }
            }

            @Override
            public void onFailure(Call<_User> call, Throwable t) {
                Logger.e("请求失败",t);
            }
        });
    }


    /**
     * 注册用户
     * @param body
     */
    public static void toRegisterUser(final IRegisterPer iRegisterPer, RegisterBody body){
        init();

        UserApi api = retrofit.create(UserApi.class);
        Call<SuccessBody> call = api.registeredUser(body);
        call.enqueue(new Callback<SuccessBody>() {
            @Override
            public void onResponse(Call<SuccessBody> call, Response<SuccessBody> response) {
                if (response.isSuccessful()){
                    iRegisterPer.showRequestInfo("注册成功");
                }else{
                    iRegisterPer.showRequestInfo("注册失败");
                }
            }
            @Override
            public void onFailure(Call<SuccessBody> call, Throwable t) {
                Logger.e("请求失败",t);
            }
        });
    }

    /**
     * 获取当前在线用户的发布物品集合
     */
    public static void toGetItemsOnline(String userName){
        init();
        ItemApi itemApi=retrofit.create(ItemApi.class);
        Call<ItemResultBody> call=itemApi.getItemByuserName(userName);
        call.enqueue(new Callback<ItemResultBody>() {
            @Override
            public void onResponse(Call<ItemResultBody> call, Response<ItemResultBody> response) {
                if (response.isSuccessful()){
                    int releasedCount=response.body().getResults().length;
                    MangoApplication.mapPut("releasedCount",releasedCount);
                    Logger.d("TAG","发布数请求成功");
                }
            }

            @Override
            public void onFailure(Call<ItemResultBody> call, Throwable t) {
                Logger.e("请求失败",t);
            }
        });

    }

    /**
     * 获取当前用户的收藏数
     * @param userName
     * @return
     */
    public static void toGetCollectOnline(String userName, final IUserInfoPer iUserInfoPer){
        init();
        CollectApi collectApi=retrofit.create(CollectApi.class);
        Call<CollectResultBody> call=collectApi.getCollectByuserName(userName);
        call.enqueue(new Callback<CollectResultBody>() {
            @Override
            public void onResponse(Call<CollectResultBody> call, Response<CollectResultBody> response) {
                if (response.isSuccessful()){
                    int collectCount=response.body().getResults().length;
                    MangoApplication.mapPut("collectCount",collectCount);
                    handle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iUserInfoPer.getNetDataSuccess();
                        }
                    },2000);
                }
            }

            @Override
            public void onFailure(Call<CollectResultBody> call, Throwable t) {
                Logger.e("请求失败",t);
            }
        });
    }
}
