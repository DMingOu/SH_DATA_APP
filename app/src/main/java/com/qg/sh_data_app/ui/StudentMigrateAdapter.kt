package com.qg.sh_data_app.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qg.sh_data_app.R
import com.qg.sh_data_app.core.bean.Migrate

/**
 * @description:适配器：学生迁移记录
 * @author: ODM
 * @date: 2020/4/8
 */

class StudentMigrateAdapter (data : MutableList<Migrate>)
    : BaseQuickAdapter<Migrate, BaseViewHolder>(R.layout.item_student_migrate_situation , data) {


    override fun convert(holder: BaseViewHolder, item: Migrate) {
        holder.setText(R.id.tv_migrate_time , item.from.time+"至"+item.to.time)
        holder.setText(R.id.tv_migrate_locations,item.from.city+"-"+item.to.city  )
    }
}