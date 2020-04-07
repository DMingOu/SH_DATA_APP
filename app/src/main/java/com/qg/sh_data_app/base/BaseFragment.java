package com.qg.sh_data_app.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @description: 基类Fragemnt
 * @author: ODM
 * @date: 2020/4/5
 */
public abstract class BaseFragment extends SupportFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    /**
     * 每次Fragment可见时必定会回调的函数
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        configStatusBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化控件
     */
    public abstract void initViews();

    /**
     * 配置
     */
    public abstract void configStatusBar();

}
