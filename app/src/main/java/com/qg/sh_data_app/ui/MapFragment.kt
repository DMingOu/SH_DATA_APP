package com.qg.sh_data_app.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ZoomControls
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.vove7.bottomdialog.BottomDialog
import cn.vove7.bottomdialog.builder.*
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.leaf.library.StatusBarUtil
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseFragment
import com.qg.sh_data_app.core.Constants
import com.qg.sh_data_app.core.bean.HeatMapData
import com.qg.sh_data_app.core.bean.HeatMapDots
import com.qg.sh_data_app.core.bean.SingleStudentMigrateData
import com.qg.sh_data_app.core.net.RetrofitManager
import com.qg.sh_data_app.databinding.FragmentMapBinding
import com.qg.sh_data_app.widget.MyAdapter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
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

    private var rvAdapter : StudentMigrateAdapter ?= null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMapBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerLiveEventObserve()
    }

    override fun onResume() {
         super.onResume()
         //执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
         binding.mapView.onResume()
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
        StatusBarUtil.setColor(_mActivity, ColorUtils.getColor(R.color.white))
    }

    override fun initViews() {
        configMapWidgets()



        binding.rvStudentMigrate.layoutManager = LinearLayoutManager(activity)
        rvAdapter = StudentMigrateAdapter(mutableListOf())
        binding.rvStudentMigrate.adapter = rvAdapter
        binding.dlStudentSituation.addTouchView(binding.tvStudentInfo)
        binding.tvStudentInfo.setOnClickListener {
            if (!binding.dlStudentSituation.isTop()) {
                binding.dlStudentSituation.toTop();
            } else {
                binding.dlStudentSituation.toBottom();
            }
        }

/*        val demo : SingleStudentMigrateData = GsonUtils.fromJson("{\n" +
                "            \"studentName\": \"李泽创\",\n" +
                "            \"studentId\": \"12345678903\",\n" +
                "            \"migrate\": [\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 118.796875,\n" +
                "                        \"lat\": 32.060253,\n" +
                "                        \"time\": \"2020-04-01\",\n" +
                "                        \"city\": \"南京市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 114.05787,\n" +
                "                        \"lat\": 22.543098,\n" +
                "                        \"time\": \"2020-04-02\",\n" +
                "                        \"city\": \"深圳市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 114.05787,\n" +
                "                        \"lat\": 22.543098,\n" +
                "                        \"time\": \"2020-04-02\",\n" +
                "                        \"city\": \"深圳市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 112.93881,\n" +
                "                        \"lat\": 28.228209,\n" +
                "                        \"time\": \"2020-04-03\",\n" +
                "                        \"city\": \"长沙市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 112.93881,\n" +
                "                        \"lat\": 28.228209,\n" +
                "                        \"time\": \"2020-04-03\",\n" +
                "                        \"city\": \"长沙市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 113.121414,\n" +
                "                        \"lat\": 23.021547,\n" +
                "                        \"time\": \"2020-04-04\",\n" +
                "                        \"city\": \"佛山市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 113.121414,\n" +
                "                        \"lat\": 23.021547,\n" +
                "                        \"time\": \"2020-04-04\",\n" +
                "                        \"city\": \"佛山市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 113.264435,\n" +
                "                        \"lat\": 23.129162,\n" +
                "                        \"time\": \"2020-04-05\",\n" +
                "                        \"city\": \"广州市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 113.264435,\n" +
                "                        \"lat\": 23.129162,\n" +
                "                        \"time\": \"2020-04-05\",\n" +
                "                        \"city\": \"广州市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 121.4737,\n" +
                "                        \"lat\": 31.230415,\n" +
                "                        \"time\": \"2020-04-06\",\n" +
                "                        \"city\": \"上海市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 121.4737,\n" +
                "                        \"lat\": 31.230415,\n" +
                "                        \"time\": \"2020-04-06\",\n" +
                "                        \"city\": \"上海市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 104.06654,\n" +
                "                        \"lat\": 30.57227,\n" +
                "                        \"time\": \"2020-04-07\",\n" +
                "                        \"city\": \"成都市\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"from\": {\n" +
                "                        \"lng\": 104.06654,\n" +
                "                        \"lat\": 30.57227,\n" +
                "                        \"time\": \"2020-04-07\",\n" +
                "                        \"city\": \"成都市\"\n" +
                "                    },\n" +
                "                    \"to\": {\n" +
                "                        \"lng\": 81.324135,\n" +
                "                        \"lat\": 43.916824,\n" +
                "                        \"time\": \"2020-04-08\",\n" +
                "                        \"city\": \"伊犁市\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }"  , SingleStudentMigrateData::class.java)

        showSingleStudentMigrateTrack(demo)*/
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

    /**
     * 注册 LiveEventBus 事件
     */
    private fun registerLiveEventObserve(){
        //粘性注册，收到展示热力图事件
        LiveEventBus
                .get(Constants.SHOW_HEAT_MAP, HeatMapData::class.java)
                .observeSticky(this, Observer<HeatMapData> {
                     showHeatMapData(it.data)
                })
        //粘性注册，收到展示特定学生迁移轨迹的事件
        LiveEventBus
                .get(Constants.SHOW_MIGRATE_TRACK , SingleStudentMigrateData::class.java)
                .observeSticky(this , Observer<SingleStudentMigrateData>{
                     showSingleStudentMigrateTrack(it)
                })
    }


    /**
     * 展示学生轨迹
     * @param data SingleStudentMigrateData
     * Todo 开线程执行
     */
    private fun showSingleStudentMigrateTrack(data : SingleStudentMigrateData){
        //地图画线
        val points : MutableList<LatLng> = mutableListOf()
        val list = data.migrate
        //获取各点坐标
        list.forEach {
            points.add(LatLng(it.from.lat , it.from.lng))
            points.add(LatLng(it.to.lat ,it.to.lng))
        }

        //纹理资源List
        val textureList : MutableList<BitmapDescriptor> = mutableListOf()
         val mRedTexture : BitmapDescriptor = BitmapDescriptorFactory
            .fromResource(R.mipmap.icon_road_red_arrow)
        val mBlueTexture : BitmapDescriptor = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_road_blue_arrow)
        val mGreenTexture : BitmapDescriptor = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_road_green_arrow)
        textureList.add(mRedTexture)
        textureList.add(mBlueTexture)
        textureList.add(mGreenTexture)
        //indexList 分配纹理对应textureList的获取顺序
        val indexList : MutableList<Int> = mutableListOf()
        var count = points.size
        while (count-- > 0){
            indexList.add(count%3)
        }

        //设置折线的属性
        val mOverlayOptions =  PolylineOptions()
        .width(18)
        .dottedLine(true)
        .points(points)
                .textureIndex(indexList)//设置纹理列表
        .customTextureList(textureList)

        //在地图上绘制折线
        val mPolyline = binding.mapView.map.addOverlay(mOverlayOptions);


        val info = "学生:  "+ data.studentName+"  "+data.studentId
        binding.tvStudentInfo.text = info
        rvAdapter?.setNewData(data = list.toMutableList())

    }

    private fun showHeatMapData( dots : List<HeatMapDots>? ){
        mDisposable = Observable.create(ObservableOnSubscribe<HeatMap > {
            val heatDotList : MutableList<WeightedLatLng> = mutableListOf()
            dots?.forEach {
                val weightedLatLng = WeightedLatLng(LatLng(it.lat ,it.lng), it.count.toDouble())
                heatDotList.add(weightedLatLng)
            }
            //设置渐变颜色值
            val DEFAULT_GRADIENT_COLORS = intArrayOf(ColorUtils.string2Int("#5FFF53") ,ColorUtils.string2Int("#FFF600")
                    ,ColorUtils.string2Int("#FF5D3B")
                    , ColorUtils.string2Int("#FE461F")
                    )
            //设置渐变颜色起始值
            val DEFAULT_GRADIENT_START_POINTS = floatArrayOf(0.2f,0.4f,0.6f, 0.9f)
            //构造颜色渐变对象
            val gradient = Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS)
            //构建具体的自定义热力图
            val mCustomHeatMap = HeatMap.Builder()
                    .weightedData(heatDotList)
                    .gradient(gradient)
                    .build()
            it.onNext(mCustomHeatMap)
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe {
              binding.mapView.map.addHeatMap(it)
          }

        val mMapStatus = MapStatus.Builder()
                        .target(LatLng( 25.0 , 113.0))
                        .zoom(7.7F) //7.7级刚好可以显示广东省
                        .build()
        val mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus)
        binding.mapView.map.animateMapStatus(mapStatusUpdate)
    }

}