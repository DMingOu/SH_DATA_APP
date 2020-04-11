package com.qg.sh_data_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SPUtils
import com.leaf.library.StatusBarUtil
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getTransitiveData()
    }

    private fun getTransitiveData() {
        // 获取由启动者Fragment传递过来的String类型数据
        val valueStr = arguments?.getString(Constants.USER_NAME) as String
        binding.tvUserNameLogout.text = valueStr
    }


    override fun initViews() {
        binding.tvLogout.setOnClickListener {
            //清除SP中的 Authorization 属性值，置值为空字符串
            SPUtils.getInstance().put(Constants.USER_AUTHORIZATION, "")
            SPUtils.getInstance().put(Constants.USER_NAME,"")
            startWithPopTo(LoginFragment(),MainFragment::class.java ,true)
        }
        binding.imvLogoutBack.setOnClickListener {
            pop()
        }
    }

    override fun configStatusBar() {
        StatusBarUtil.setColor(this._mActivity, ColorUtils.string2Int("#F6F6F6"))
        StatusBarUtil.setDarkMode(_mActivity)
    }
}