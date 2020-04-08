package com.qg.sh_data_app.core.bean
import com.google.gson.annotations.SerializedName


/**
 * @description: 登录请求 实体类
 * @author: ODM
 * @date: 2020/4/8
 */
data class LoginRequest(

    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)