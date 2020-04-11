package com.qg.sh_data_app.ui.login

import android.R.attr.fragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.Fade
import com.blankj.utilcode.util.ClickUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.leaf.library.StatusBarUtil
import com.qg.sh_data_app.R
import com.qg.sh_data_app.base.BaseMVVMFragment
import com.qg.sh_data_app.databinding.FragmentLoginBinding
import com.qg.sh_data_app.ui.MainFragment


/**
 * @description: 登录页 Fragment MVVM模式
 * @author: ODM
 * @date: 2020/4/8
 */
class LoginFragment : BaseMVVMFragment() {

    private var _binding : FragmentLoginBinding ?= null
    private val binding get() = _binding!!

    private var viewModel : LoginViewModel ?= null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater , container , false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        return binding.root
    }

    override fun configStatusBar() {
        StatusBarUtil.setColor(this._mActivity, ColorUtils.getColor(R.color.white))
        StatusBarUtil.setDarkMode(_mActivity)
    }

    override fun initViewModelObserve() {
        viewModel?.apply {
            loginCallback?.observe(this@LoginFragment, Observer {
                    //进入主页面，并关闭登录页面
                    ToastUtils.showShort("登录成功")

                    startWithPop(MainFragment())

            })
        }
    }

    override fun initViews() {
/*        //临时账号密码，方便测试
        binding.etAccountLogin.setText("test")
        binding.etPasswordLogin.setText("a123456")*/

        //登录按钮的注册事件--防止多次点击
        binding.btnLogin.setOnClickListener(object : ClickUtils.OnMultiClickListener(2) {
            override fun onTriggerClick(v: View) {
            }
            override fun onBeforeTriggerClick(v: View, count: Int) {
                if(count == 1 ){
                    viewModel?.loginRequest(binding.etAccountLogin.text.toString() , binding.etPasswordLogin.text.toString())
                }
            }
        })
    }
}