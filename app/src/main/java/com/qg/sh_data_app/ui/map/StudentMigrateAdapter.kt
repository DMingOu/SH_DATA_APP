package com.qg.sh_data_app.ui.map

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qg.sh_data_app.R
import com.qg.sh_data_app.core.bean.Migrate
import com.qg.sh_data_app.core.bean.TwoOrMoreData

/**
 * @description:地图页抽屉内容列表适配器：学生迁移记录
 * @author: ODM
 * @date: 2020/4/8
 */

class StudentMigrateAdapter (data : MutableList<TwoOrMoreData.DataBean.MigrateBean>)
    : BaseQuickAdapter<TwoOrMoreData.DataBean.MigrateBean, BaseViewHolder>(R.layout.item_student_migrate_situation , data) {


    override fun convert(holder: BaseViewHolder, item: TwoOrMoreData.DataBean.MigrateBean) {
        holder.setText(R.id.tv_migrate_time , item.from.time+"至"+item.to.time)
        holder.setText(R.id.tv_migrate_locations,item.from.city+"-"+item.to.city  )
    }
}