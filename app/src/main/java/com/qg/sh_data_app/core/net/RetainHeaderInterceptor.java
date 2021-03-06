package com.qg.sh_data_app.core.net;

import android.provider.SyncStateContract;

import com.blankj.utilcode.util.SPUtils;
import com.qg.sh_data_app.core.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @description: 持久化&请求前附带 Header 字段 Okhttp拦截器类
 * @author: ODM
 * @date: 2020/4/7
 */
public class RetainHeaderInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        //若Authorization 字段的值非空字符串
        //则在请求前，附带上 Authorization 字段的值
        if (!"".equals(SPUtils.getInstance().getString(Constants.USER_AUTHORIZATION,""))) {
            String authorization = SPUtils.getInstance().getString(Constants.USER_AUTHORIZATION);
            builder.addHeader("Authorization", authorization);
        }

        Request request = builder.build();

        Response originalResponse = chain.proceed(request);
/*        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            for (String cookie : cookies) {
                LogUtils.d(cookie); //循环打印
            }
            SPUtils.getInstance().put("USER_COOKIES", cookies); //用SharedPreferences 存储Cookies
        }*/

        //持久化响应报文中的 Authorization 属性
        if( originalResponse.header("Authorization") != null) {
            String authorization = originalResponse.header("Authorization");
            if (authorization != null) {
                SPUtils.getInstance().put(Constants.USER_AUTHORIZATION,authorization);
            }
        }

        return originalResponse;
    }
}