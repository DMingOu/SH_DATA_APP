package com.qg.sh_data_app.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ZoomControls
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.blankj.utilcode.util.ToastUtils
import com.leaf.library.StatusBarUtil
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseFragment
import com.qg.sh_data_app.core.bean.HeatMapData
import com.qg.sh_data_app.core.bean.HeatMapDots
import com.qg.sh_data_app.core.net.RetrofitManager
import com.qg.sh_data_app.databinding.FragmentMapBinding
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * @description: 地图页面Fragment
 * @author: ODM
 * @date: 2020/4/7
 */
class MapFragment : BaseFragment() {

    private var _binding : FragmentMapBinding ?= null
    private val binding get() = _binding!!

    private  var mDisposable : Disposable ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMapBinding.inflate(inflater , container , false)
        return binding.root
    }

     override fun onResume() {
         super.onResume()
         //执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
         binding.mapView.onResume()
         getHeatMapData()
    }

     override fun onPause() {
        super.onPause()
         //onPause时执行mMapView. onPause ()，实现地图生命周期管理
         binding.mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

     override fun onDestroy() {
        super.onDestroy()
        //执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
         _binding = null
         //断开本次网络请求
         if(mDisposable?.isDisposed == false){
             mDisposable?.dispose()
         }
    }

    override fun configStatusBar() {
        StatusBarUtil.setDarkMode(_mActivity)
        StatusBarUtil.setColor(_mActivity, resources.getColor(R.color.white))
    }

    override fun initViews() {
        configMapWidgets()
    }

    /**
     * 对百度地图内部的元素进行配置
     */
    private fun configMapWidgets(){

        //隐藏指南针
        binding.mapView.map.getUiSettings().setCompassEnabled(false);
        // 隐藏logo
        val child: View = binding.mapView.getChildAt(1)
        if (child != null && (child is ImageView || child is ZoomControls)) {
            child.visibility = View.INVISIBLE
        }
        //隐藏比例尺图标
        binding.mapView.showScaleControl(false)
        //隐藏放大缩小按钮
        binding.mapView.showZoomControls(false)
        //隐藏地图POI
//        binding.mapView.map.showMapPoi(false)
    }

    private fun getHeatMapData(){
        var heatMapData : HeatMapData ?= null
        //测试：获取热力图数据
          RetrofitManager.getInstance()
                .apiService
                .getHeatMapData("2020-04-04")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<HeatMapData>{
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(data: HeatMapData) {
                        heatMapData = data
                    }

                    override fun onError(e: Throwable) {
                        ToastUtils.showShort("网络请求错误 " + e.message)
                        Logger.e(e.message ?:"")
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        showHeatMapData(heatMapData?.data)
                    }
                })
    }

    private fun showHeatMapData( dots : List<HeatMapDots>? ){
        val observable: Observable<HeatMap> = Observable.create(ObservableOnSubscribe<HeatMap > {
            val heatDotList : MutableList<WeightedLatLng> = mutableListOf()
            dots?.forEach {
                val weightedLatLng = WeightedLatLng(LatLng(it.lat ,it.lng), it.count.toDouble())
                heatDotList.add(weightedLatLng)
            }
            //设置渐变颜色值
            val DEFAULT_GRADIENT_COLORS = intArrayOf(Color.rgb(102, 225, 0), Color.rgb(255, 0, 0))
            //设置渐变颜色起始值
            val DEFAULT_GRADIENT_START_POINTS = floatArrayOf(0.2f, 1f)
            //构造颜色渐变对象
            val gradient = Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS)
            val mCustomHeatMap = HeatMap.Builder()
                    .weightedData(heatDotList)
                    .gradient(gradient)
                    .build()
            it.onNext(mCustomHeatMap)
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())

        val disposable: Disposable = observable.subscribe {
            binding.mapView.map.addHeatMap(it)
        }
        val mMapStatus = MapStatus.Builder()
                        .target(LatLng( 25.0 , 113.0))
                        .zoom(7.7F) //8级刚好可以显示广东省
                        .build()
        val mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus)
        binding.mapView.map.animateMapStatus(mapStatusUpdate)

    }

}