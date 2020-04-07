package com.qg.sh_data_app.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @description: MVVM模式的Fragment基类
 * @author: ODM
 * @date: 2020/4/8
 */
public abstract class BaseMVVMFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            initViewModelObserve();
    }

    /**
     * 动态观察ViewModel的变量
     */
    public abstract void initViewModelObserve();
}
