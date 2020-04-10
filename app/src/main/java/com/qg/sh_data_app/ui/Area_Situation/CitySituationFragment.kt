package com.qg.sh_data_app.ui.Area_Situation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ClickUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.leaf.library.StatusBarUtil
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseMVVMFragment
import com.qg.sh_data_app.core.Constants
import com.qg.sh_data_app.core.bean.HeatMapData
import com.qg.sh_data_app.core.bean.TwoOrMoreData
import com.qg.sh_data_app.databinding.FragmentCitiesSituationBinding
import com.qg.sh_data_app.ui.map.MapFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @description: 城市打卡情况页面Fragment
 * @author: ODM
 * @date: 2020/4/7
 */
class CitySituationFragment : BaseMVVMFragment() {

    private var _binding : FragmentCitiesSituationBinding ?= null
    private val binding get() = _binding!!

    private var rvAdapter : AreaSituationAdapter ?= null
    private var viewModel : AreaSituationViewModel ?= null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCitiesSituationBinding.inflate(inflater , container , false)
        viewModel = ViewModelProviders.of(this).get(AreaSituationViewModel::class.java)
        return binding.root
    }

        override fun configStatusBar() {
            StatusBarUtil.setColor(this._mActivity, resources.getColor(R.color.title_bar_city_situation))
            StatusBarUtil.setLightMode(_mActivity)
        }

    override fun initViews() {
        //标题栏左方返回按钮的点击事件，弹出当前Fragment
        binding.ivBackCitySituation.setOnClickListener {
            pop()
        }
        registerLiveEventObserve()
        //对RecyclerView进行配置
        //初始化 RecyclerView 的适配器
        rvAdapter = AreaSituationAdapter(mutableListOf())
        binding.rvAreaSituation.layoutManager = LinearLayoutManager(activity)

        binding.rvAreaSituation.adapter = rvAdapter
        rvAdapter?.animationEnable = true
        rvAdapter?.setOnItemClickListener { adapter, view, position ->
            //尚未定义点击事件
        }
        //动态添加RecyclerView 的头布局 --按钮点击查看热力图
        val btnHeaderView : View = layoutInflater.inflate(R.layout.item_header_rv_area_situation, binding.rvAreaSituation, false)
        rvAdapter?.addHeaderView(btnHeaderView)
        //点击查看热力图 & 防止多次点击启动多个Fragment
        rvAdapter?.headerLayout?.setOnClickListener(object : ClickUtils.OnMultiClickListener(2) {
            override fun onTriggerClick(v: View) {
            }
            override fun onBeforeTriggerClick(v: View, count: Int) {
                if(count == 1 ){
                    viewModel?.postShowHeatMapEvent()
//                    var data: HeatMapData ?= null
//                    LiveEventBus.get(Constants.SHOW_HEAT_MAP).post(data)
                    start(MapFragment())
                }
            }
        })
    }

    override fun initViewModelObserve() {
        viewModel?.apply {
            areaData.observe(this@CitySituationFragment , Observer {
                rvAdapter?.setNewData(it.data.toMutableList())
            })
        }
    }

    private fun registerLiveEventObserve(){
        LiveEventBus.get("oneTime", String::class.java).observeSticky(this,Observer<String>{
            viewModel?.getAreaSituationData(it)
            binding.tvTitleTimeCitySituation.setText(it)
        })
    }
}