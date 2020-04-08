package com.qg.sh_data_app.core.net;



import com.qg.sh_data_app.core.bean.HeatMapData;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.bean.LoginCallback;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description: Retrofit 网络请求接口定义类
 * @author: ODM
 * @date: 2020/4/7
 */
public interface ApiService {

     String BASE_URL = "http://39.98.41.126:10020/";


//    @GET("https://oss.mapmiao.com/others/ncov/data.json?timestamp=999999999999999")
//    Observable<ResponseBody> getPoisArea();


    /**
     * 根据单日日期，获取此日期的热力图数据
     * @param timeString 表示日期的字符串
     * @return 热力图数据
     */
    @GET("api/student/heatMap")
    Observable<HeatMapData> getHeatMapData(@Query("time") String timeString);

    /**
     * 迁移数据搜索（包括搜索某段时间的全部迁移学生和搜索某段时间内某个学生）
     * @return 搜索结果
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/student/migrate")
    Observable<TwoOrMoreData> getSearchResultData(@Body RequestBody info);
    /**
     * 根据请求调用登录api
     * @param loginRequestBody 登录请求Body
     * @return 登录回调
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api/user/login")
    Observable<LoginCallback> postLoginRequest(@Body RequestBody loginRequestBody);


}
