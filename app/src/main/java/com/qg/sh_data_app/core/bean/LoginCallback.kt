package com.qg.sh_data_app.core.bean
import com.google.gson.annotations.SerializedName


/**
 * @description: 登录数据回调实体类
 * @author: ODM
 * @date: 2020/4/8
 */
data class LoginCallback(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String
)