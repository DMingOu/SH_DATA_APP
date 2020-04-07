package com.qg.sh_data_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qg.sh_data_app.base.BaseFragment
import com.qg.sh_data_app.databinding.FragmentCitiesSituationBinding

/**
 * @description: 城市情况页面Fragment
 * @author: ODM
 * @date: 2020/4/7
 */
class CitySituationFragment : BaseFragment() {

    private var _binding : FragmentCitiesSituationBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCitiesSituationBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun initViews() {
        //标题栏左方返回按钮的点击事件
        binding.ivBackCitySituation.setOnClickListener {
            pop()
        }
    }
}