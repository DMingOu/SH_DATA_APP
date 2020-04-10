package com.qg.sh_data_app.ui.Sign_Situation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.core.bean.TimeIntervalRequest
import com.qg.sh_data_app.core.bean.UnFinishSituation
import com.qg.sh_data_app.core.bean.UnFinishStudentsData

import com.qg.sh_data_app.core.net.RetrofitManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/**
 * @description: 未完成(打卡动态)情况 ViewModel
 * @author: ODM
 * @date: 2020/4/9
 */

class UnFinishedSituationViewModel : ViewModel(){


    private  var _situationData = MutableLiveData<List<UnFinishStudentsData>>()
    val situationData : LiveData<List<UnFinishStudentsData>> ?= _situationData

    companion object{
        const val tag = "UnFinishedSituationViewModel"
    }



    fun getUnFinishedSituationDataRequest( startDate : String ?){
        val currentDate = TimeUtils.millis2String(System.currentTimeMillis())
        //构造时间请求
        val  t = TimeIntervalRequest(startDate ?:"2020-04-01",currentDate)
        val bodyString = GsonUtils.toJson(t)
        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), bodyString);
        //调用Retrofit
        RetrofitManager.getInstance()
                .apiService
                .postUnFinishSituationRequest(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object  : Observer<UnFinishSituation> {
                    override fun onComplete() {
                    }
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(callback: UnFinishSituation) {
                        if(callback.code == "1"){
                            //数据正常，给LiveData赋值
                            _situationData.value = callback.data
                        }
                    }
                    override fun onError(e: Throwable) {
                        ToastUtils.showShort(e.message)
                    }
                })
    }


}