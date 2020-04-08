package com.qg.sh_data_app.ui.Area_Situation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baidu.mapapi.map.HeatMap
import com.blankj.utilcode.util.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.core.Constants
import com.qg.sh_data_app.core.bean.HeatMapData
import com.qg.sh_data_app.core.bean.HeatMapDots
import com.qg.sh_data_app.core.net.RetrofitManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @description: 地区打卡情况ViewModel
 * @author: ODM
 * @date: 2020/4/8
 */
class AreaSituationViewModel :ViewModel() {

    //viewModel内部更新的MutableLiveData
    private val _areaData  =  MutableLiveData<HeatMapData>()
    //供外部调用的LiveData
    val areaData : LiveData<HeatMapData> = _areaData


    companion object{
        const val tag = "AreaSituationViewModel"
    }

    init {
        //初始化ViewModel时自动拉取数据
        getAreaSituationData()
    }

    private fun getAreaSituationData(){
        RetrofitManager.getInstance()
                .apiService
                .getHeatMapData("2020-04-04")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<HeatMapData> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(heatMapData: HeatMapData) {
                        _areaData.value = heatMapData
                    }

                    override fun onError(e: Throwable) {
                        ToastUtils.showShort("网络请求错误 " + e.message)
                        e.printStackTrace()
                    }

                    override fun onComplete() {
//                        showHeatMapData(heatMapData?.data)
                    }
                })
    }

     fun postShowHeatMapEvent(){
        LiveEventBus
                .get(Constants.SHOW_HEAT_MAP)
                .post(_areaData.value);
     }

}