package com.qg.sh_data_app.ui.Sign_Situation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.leaf.library.StatusBarUtil
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseMVVMFragment
import com.qg.sh_data_app.databinding.FragmentUnfinishSituationBinding
import me.yokeyword.fragmentation.anim.FragmentAnimator


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

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        //懒加载时完全进入页面，才发出请求
        viewModel?.getUnFinishedSituationDataRequest("2020-04-01")
    }

    override fun initViewModelObserve() {
        viewModel?.apply {
            situationData?.observe(this@UnFinishSituationFragment, Observer {
                //给列表赋值
                if(it.isNotEmpty()){
                    rvAdapter?.setNewData(it.toMutableList())
                    collapseAllItem()
                }
            })
        }
    }

    private fun  collapseAllItem(){
        rvAdapter?.let {
            var size : Int = it.data.size
            while (size-- > 0){
                it.collapse(size)
            }
        }
    }

    override fun initViews() {
        //左上角的返回键点击事件
        binding.ivBackSignSituation.setOnClickListener {
            pop()
        }

        //初始化 列表 和 适配器

        rvAdapter = UnFinishedSituationAdapter()
        binding.rvUnfinishedSituation.layoutManager = LinearLayoutManager(activity)
        binding.rvUnfinishedSituation.adapter = rvAdapter
        rvAdapter?.animationEnable = true
        rvAdapter?.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)
    }

     override fun onCreateFragmentAnimator(): FragmentAnimator? {
         // 获取在SupportActivity里设置的全局动画对象，进行修改
         val fragmentAnimator: FragmentAnimator = super.onCreateFragmentAnimator()
         fragmentAnimator.enter = R.anim.v_fragment_enter
         fragmentAnimator.exit = R.anim.v_fragment_exit
         fragmentAnimator.popEnter = R.anim.no_anim
         fragmentAnimator.popExit = R.anim.no_anim
         return fragmentAnimator
        // 也可以直接通过
        // return new FragmentAnimator(enter,exit,popEnter,popExit)设置一个全新的动画
    }

}