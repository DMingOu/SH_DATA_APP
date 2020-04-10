package com.qg.sh_data_app.ui.Area_Situation

import com.baidu.mapapi.map.HeatMap
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qg.sh_data_app.R
import com.qg.sh_data_app.core.bean.HeatMapDots

/**
 * @description: 地区数据RecyclerView Adapter
 * @author: ODM
 * @date: 2020/4/7
 */
class AreaSituationAdapter (data : MutableList<HeatMapDots>)
    : BaseQuickAdapter<HeatMapDots, BaseViewHolder>(R.layout.item_area_situation , data) {


    override fun convert(holder: BaseViewHolder, item: HeatMapDots) {
        if("" == item.city || item.count < 0) return
        holder.setText(R.id.tv_area_name , item.city)
        holder.setText(R.id.tv_student_count,item.count.toString())
    }
}