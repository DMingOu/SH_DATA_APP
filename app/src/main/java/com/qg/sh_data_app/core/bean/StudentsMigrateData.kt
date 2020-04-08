package com.qg.sh_data_app.core.bean
import com.google.gson.annotations.SerializedName


/**
 * @description: 学生迁移数据
 * @author: ODM
 * @date: 2020/4/8
 */

data class StudentsMigrateData(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<SingleStudentMigrateData>,
    @SerializedName("message")
    val message: String
)

data class SingleStudentMigrateData(
    @SerializedName("migrate")
    val migrate: List<Migrate>,
    @SerializedName("studentId")
    val studentId: String,
    @SerializedName("studentName")
    val studentName: String
)

data class Migrate(
    @SerializedName("from")
    val from: From,
    @SerializedName("to")
    val to: To
)

data class From(
    @SerializedName("city")
    val city: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("time")
    val time: String
)

data class To(
    @SerializedName("city")
    val city: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("time")
    val time: String
)