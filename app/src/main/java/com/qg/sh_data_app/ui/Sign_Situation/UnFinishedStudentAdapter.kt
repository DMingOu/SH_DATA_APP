package com.qg.sh_data_app.ui.Sign_Situation

import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qg.sh_data_app.R
import com.qg.sh_data_app.core.bean.UnFinishStudent

/**
 * @description: 未完成学生列表适配器
 * @author: ODM
 * @date: 2020/4/9
 */
class UnFinishedStudentAdapter :BaseNodeProvider() {

    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_student_unfinished

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity : UnFinishStudent ?= item as UnFinishStudent
        helper.setText(R.id.tv_student_name_unfinished  ,entity?.name)
        helper.setText(R.id.tv_student_number_unfinished  ,entity?.id)


    }
}