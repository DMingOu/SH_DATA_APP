package com.qg.sh_data_app.ui.Sign_Situation

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.R
import com.qg.sh_data_app.core.bean.UnFinishStudentsData

/**
 * @description: 未完成时间列表适配器
 * @author: ODM
 * @date: 2020/4/9
 */
class UnFinishedTimeIntervalAdapter:BaseNodeProvider() {

    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = R.layout.item_time_unfinished

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity:UnFinishStudentsData ?= item as UnFinishStudentsData
        helper.setText(R.id.tv_time_unfinished , entity?.time)
        helper.setText(R.id.tv_count_unfinished , entity?.students?.size.toString() )
        setArrowSpin(helper , item , true)
    }

    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        for (payload in payloads) {
            if (payload is Int && payload == UnFinishedSituationAdapter.EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, item, true)
            }
        }
    }

    private fun setArrowSpin(
            helper: BaseViewHolder,
            data: BaseNode?,
            isAnimate: Boolean
    ) {
        val entity: UnFinishStudentsData ? = data as UnFinishStudentsData?
        val imageView = helper.getView<ImageView>(R.id.iv_guide_unfinished)
        if (entity?.isExpanded == true) {
            //已展开状态
            if (isAnimate){
                ViewCompat.animate(imageView).setDuration(200)
                        .setInterpolator(DecelerateInterpolator())
                        .rotation(180f)
                        .start()
            } else {
                imageView.rotation = 0f
            }
        } else {
            //未展开状态
            imageView.setImageResource(R.mipmap.icon_guide_arrow)
            imageView.rotation = 0f
        }
    }

    override fun onClick(
            helper: BaseViewHolder,
            view: View,
            data: BaseNode,
            position: Int
    ) {
        getAdapter()?.expandOrCollapse(
                position,
                true,
                true,
                UnFinishedSituationAdapter.EXPAND_COLLAPSE_PAYLOAD
        )

    }
}