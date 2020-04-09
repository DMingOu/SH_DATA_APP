package com.qg.sh_data_app.core.bean
import com.google.gson.annotations.SerializedName


/**
 * @description: 时间范围 请求实体类
 * @author: ODM
 * @date: 2020/4/9
 */
/*例子：
{
	"startTime":"2020-04-01",
	"endTime":"2020-04-08"
}
*/
data class TimeIntervalRequest(
    //时间字符串必须符合格式：yyyy-mm-dd
        @SerializedName("startTime")
        val startTime: String ,
        @SerializedName("endTime")
        val endTime: String

)