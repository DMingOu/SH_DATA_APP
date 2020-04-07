package com.qg.sh_data_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qg.sh_data_app.base.BaseFragment
import com.qg.sh_data_app.databinding.FragmentMapBinding

/**
 * @description: 地图页面Fragment
 * @author: ODM
 * @date: 2020/4/7
 */
class MapPage : BaseFragment() {

    private var _binding : FragmentMapBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMapBinding.inflate(inflater , container , false)
        return binding.root
    }

     override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        binding.mapView.onResume()
    }

     override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
         binding.mapView.onPause()
    }

     override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
         binding.mapView.onDestroy()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initViews() {
    }
}