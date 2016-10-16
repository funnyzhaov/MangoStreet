package me.funnyzhao.mangostreet.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by funnyzhao .
 * 网络连接工具类
 */

public class NetWorkUtil {

    public static boolean isNetConnect(@NonNull Context context) {
        ConnectivityManager service = (ConnectivityManager) context.getApplicationContext().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = service.getActiveNetworkInfo();

        return info != null && info.isAvailable();
    }
}
