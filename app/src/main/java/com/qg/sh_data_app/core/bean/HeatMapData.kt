package com.qg.sh_data_app.core.bean
import com.google.gson.annotations.SerializedName


/**
 * @description: 热力图数据实体类
 * @author: ODM
 * @date: 2020/4/7
 */
data class HeatMapData(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<HeatMapDots>,
    @SerializedName("message")
    val message: String
)

data class HeatMapDots(
    @SerializedName("count")
    val count: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)