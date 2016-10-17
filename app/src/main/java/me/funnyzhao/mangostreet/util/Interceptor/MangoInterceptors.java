package me.funnyzhao.mangostreet.util.Interceptor;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by funnyzhao .
 * 自定义拦截器
 */

public class MangoInterceptors implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {

            //封装headers
            Request request = chain.request().newBuilder()
                    .header("Content-Type", "application/json") //添加请求头信息
                    .header("X-Bmob-Application-Id","9b00125d964e776ec5b4c79c06dd6c05")
                    .header("X-Bmob-REST-API-Key","f69e70ef1a410db0343f21ffc2ea8b7e")
                    .build();
            Headers headers = request.headers();
            //打印
            Logger.d("Content-Type is :"+headers.get("Content-Type"));
            String requestUrl = request.url().toString(); //获取请求url地址
            String methodStr = request.method(); //获取请求方式
            RequestBody body = request.body(); //获取请求body
            String bodyStr = (body==null?"":body.toString());
            //打印Request数据
            Logger.d("Request Url is :"+requestUrl + "\nMethod is : " + methodStr + "\nRequest Body is :" + bodyStr + "\n");
            Response response = chain.proceed(request);
            if (response != null) {
                Logger.d("Response is not null");
            } else {
                Logger.d("Response is null");
            }
            return response;
        }
}
