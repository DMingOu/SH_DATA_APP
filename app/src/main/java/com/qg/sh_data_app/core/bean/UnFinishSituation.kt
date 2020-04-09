package com.qg.sh_data_app.core.bean
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName


/**
 * @description: 未打卡学生每日情况
 * @author: ODM
 * @date: 2020/4/9
 */
data class UnFinishSituation(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<UnFinishStudentsData>,
    @SerializedName("message")
    val message: String
)

data class UnFinishStudentsData(
    @SerializedName("students")
    val students: List<UnFinishStudent>,
    @SerializedName("time")
    val time: String
) : BaseExpandNode(){
    override val childNode: MutableList<BaseNode>?
        get() = students.toMutableList() as MutableList<BaseNode>
}


data class UnFinishStudent(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) : BaseExpandNode(){
    override val childNode: MutableList<BaseNode>?
        get() = null
}