package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;

/**
 * @description: 主界面Fragment,功能菜单
 * @author: ODM
 * @date: 2020/4/6
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main , container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initViews() {

    }

    @Override
    public boolean onBackPressedSupport() {
        ToastUtils.showShort(TAG + "触发了返回键事件。返回给Activity处理" );
        //主页面返回键触发退出App
        return super.onBackPressedSupport();
    }
}
