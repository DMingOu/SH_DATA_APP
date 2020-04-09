package com.qg.sh_data_app.ui.Sign_Situation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ClickUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.leaf.library.StatusBarUtil
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseMVVMFragment
import com.qg.sh_data_app.databinding.FragmentUnfinishSituationBinding
import com.qg.sh_data_app.ui.map.MapFragment

/**
 * @description: 未打卡人数情况页面
 * @author: ODM
 * @date: 2020/4/9
 */
class UnFinishSituationFragment : BaseMVVMFragment(){

    private var _binding : FragmentUnfinishSituationBinding ?= null
    private val binding get() = _binding!!
    private var viewModel : UnFinishedSituationViewModel ?= null
    private var rvAdapter : UnFinishedSituationAdapter ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUnfinishSituationBinding.inflate(inflater , container , false)
        viewModel = ViewModelProviders.of(this).get(UnFinishedSituationViewModel::class.java)

        return binding.root
    }

    override fun configStatusBar() {
        StatusBarUtil.setColor(this._mActivity, resources.getColor(R.color.title_bar_city_situation))
        StatusBarUtil.setLightMode(_mActivity)
    }


    override fun initViewModelObserve() {
        viewModel?.apply {
            situationData?.observe(this@UnFinishSituationFragment, Observer {
                //给列表赋值
                if(it.isNotEmpty()){
                    Logger.d(it)
                    rvAdapter?.setNewData(it.toMutableList())
//                    rvAdapter?.setDiffNewData(it.toMutableList())
                }
            })
        }
    }

    override fun initViews() {
        binding.ivBackSignSituation.setOnClickListener {
            pop()
        }

        //初始化 列表 和 适配器

        rvAdapter = UnFinishedSituationAdapter()
        binding.rvUnfinishedSituation.layoutManager = WrapContentLinearLayoutManager(activity)
        binding.rvUnfinishedSituation.adapter = rvAdapter
        rvAdapter?.animationEnable = true
        rvAdapter?.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)

    }



}