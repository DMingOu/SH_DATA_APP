package com.qg.sh_data_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.SPUtils
import com.qg.sh_data_app.base.BaseFragment
import com.qg.sh_data_app.core.Constants
import com.qg.sh_data_app.databinding.FragmentLogoutBinding
import com.qg.sh_data_app.ui.MainFragment

/**
 * @description: 退出登录页面
 * @author: ODM
 * @date: 2020/4/8
 */

class LogoutFragment : BaseFragment(){

    private var _binding : FragmentLogoutBinding ?= null
    private val binding get() = _binding!!



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogoutBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun initViews() {
        binding.tvLogout.setOnClickListener {
            //清除SP中的 Authorization 属性值，置值为空字符串
            SPUtils.getInstance().put(Constants.USER_AUTHORIZATION, "")
            startWithPopTo(LoginFragment(),MainFragment::class.java ,true)
        }
    }

    override fun configStatusBar() {

    }
}