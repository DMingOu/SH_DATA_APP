package com.qg.sh_data_app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import com.qg.sh_data_app.core.bean.LoginCallback
import com.qg.sh_data_app.core.bean.LoginRequest
import com.qg.sh_data_app.core.net.RetrofitManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/**
 * @description: 登录模块 ViewModel
 * @author: ODM
 * @date: 2020/4/8
 */
class LoginViewModel : ViewModel() {

    private  var _loginCallback = MutableLiveData<LoginCallback>()
    val loginCallback : LiveData<LoginCallback> ?= _loginCallback

    companion object{
        const val tag = "LoginViewModel"
    }


    fun loginRequest( number : String , password : String){
        if("".equals(number) && !"".equals(password)){
            ToastUtils.showShort("账号不可以为空")
            return
        } else if("".equals(password) && !"".equals(number)){
            ToastUtils.showShort("密码不可以为空")
            return
        } else if("".equals(password) && "".equals(number)){
            ToastUtils.showShort("请填写账号密码")
            return
        }
        val l = LoginRequest(number , password)
        val bodyString = GsonUtils.toJson(l)
        Logger.d(bodyString)
        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), bodyString);
        //调用Retrofit
        RetrofitManager.getInstance()
                .apiService
                .postLoginRequest(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object  : Observer<LoginCallback>{
                    override fun onComplete() {
                    }
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(callback: LoginCallback) {
                        if(callback.code == 1){
                            //账号密码正确，登录成功
                            _loginCallback.value =  callback
                        }
                        if (callback.code == -1){
                            //账号密码错误，登录失败，Toast提示用户错误原因
                            ToastUtils.showShort(callback.message)
                        }
                    }
                    override fun onError(e: Throwable) {
                            ToastUtils.showShort(e.message)
                    }
                })
    }








}