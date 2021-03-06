package com.qg.sh_data_app.core.net;



import com.qg.sh_data_app.core.bean.HeatMapData;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.bean.LoginCallback;
import com.qg.sh_data_app.core.bean.UploadResult;

import java.io.File;
import java.util.List;

import com.qg.sh_data_app.core.bean.UnFinishSituation;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    /**
     * 上传文件
     * @param
     * @return
     */
    @Multipart
    @POST("api/student/import")
    Observable<UploadResult> upload(@Part List<MultipartBody.Part> partList);

    /**
     * 根据请求Post调用获取未打卡情况数据
     * @param timeIntervalRequestBody 请求未打卡情况Body
     * @return 未打卡情况回调
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api/student/clock")
    Observable<UnFinishSituation> postUnFinishSituationRequest(@Body RequestBody timeIntervalRequestBody);
}
