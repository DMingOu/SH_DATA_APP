package com.qg.sh_data_app.ui.Sign_Situation

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.qg.sh_data_app.core.bean.UnFinishStudent
import com.qg.sh_data_app.core.bean.UnFinishStudentsData

/**
 * @description: 未完成打卡学生情况 总适配器
 * @author: ODM
 * @date: 2020/4/9
 */
class UnFinishedSituationAdapter : BaseNodeAdapter() {

    companion object {
        const val EXPAND_COLLAPSE_PAYLOAD = 90
    }

    init {
        addNodeProvider(UnFinishedTimeIntervalAdapter()) //时间列表适配器
        addNodeProvider(UnFinishedStudentAdapter()) //学生列表适配器
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node : BaseNode ?= data[position]
        if(node is UnFinishStudentsData){
            return 1
        } else if(node is UnFinishStudent){
            return 2
        }
        return -1
    }
}
