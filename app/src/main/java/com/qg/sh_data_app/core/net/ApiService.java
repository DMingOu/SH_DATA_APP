package com.qg.sh_data_app.core.net;



import com.qg.sh_data_app.core.bean.HeatMapData;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
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
    @POST("api/student/heatMap")
    Observable<HeatMapData> getHeatMapData(@Query("time") String timeString);


}
