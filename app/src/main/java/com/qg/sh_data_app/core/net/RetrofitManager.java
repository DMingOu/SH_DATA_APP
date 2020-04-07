package com.qg.sh_data_app.core.net;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description: Retrofit工具类
 * @author: ODM
 * @date: 2020/4/7
 */
public class RetrofitManager {

        private static RetrofitManager retrofitManager;
        private ApiService service;
        private static int DEFAULT_TIME_OUT = 8;
        private Retrofit retrofit;

        /**
         * 超时时间，默认为8秒
         * 有需要就用SP存储
         */
        private static int timeoutTime = DEFAULT_TIME_OUT;
        /**
         * 服务器ip地址
         */

        private static String baseUrl = ApiService.BASE_URL;

         /**
          * Cookie 持久化
          */
        public final static HashMap<String, List<Cookie>> COOKIE_STORE = new HashMap<>();



        private RetrofitManager(){
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                            COOKIE_STORE.put(httpUrl.host(),list);
                        }
                        @NotNull
                        @Override
                        public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                            List<Cookie> cookies = COOKIE_STORE.get(httpUrl.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    })
                    .addInterceptor(new RetainHeaderInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor())
//                  .addInterceptor(new AddCookiesInterceptor())
                    .retryOnConnectionFailure(true)
                    .connectTimeout(timeoutTime, TimeUnit.SECONDS)
                    .writeTimeout(timeoutTime,TimeUnit.SECONDS)
                    .readTimeout(timeoutTime,TimeUnit.SECONDS);

            //创建Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiService.class);
        }

        /**
         * 获取网络管理的manager
         * @return 该单例类
         */
        public static RetrofitManager getInstance(){
            if(retrofitManager == null){
                synchronized (Object.class){
                    if(retrofitManager == null){
                        retrofitManager = new RetrofitManager();
                    }
                }
            }
            return retrofitManager;
        }

        /**
         * 获取访问http的service
         * @return HttpService
         */
        public ApiService getApiService() {
            return service;
        }

        /**
         * 重新设置服务器和超时时间
         * @param timeout 超时时间
         * @param url 服务器地址
         */
        public static void setTimeoutAndUrl(int timeout,String url){
            timeoutTime = timeout;
            baseUrl = url;
            //重新生成service
            retrofitManager = new RetrofitManager();
        }

        public static int getTimeoutTime(){
            return timeoutTime;
        }
}

