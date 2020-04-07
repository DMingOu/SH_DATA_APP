package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.leaf.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.databinding.FragmentMainBinding;

/**
 * @description: 主界面Fragment,功能菜单
 * @author: ODM
 * @date: 2020/4/6
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBarUtil.setDarkMode(this._mActivity);
        StatusBarUtil.setColor(this._mActivity, getResources().getColor(R.color.white));
        binding = FragmentMainBinding.inflate(inflater , container ,false);
        return binding.getRoot();
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
    public void configStatusBar() {
        StatusBarUtil.setDarkMode(this._mActivity);
        StatusBarUtil.setColor(this._mActivity, getResources().getColor(R.color.white));
    }

    @Override
    public void initViews() {
        //增加数据的点击事件
        binding.btnAddDataMain.setOnClickListener( v -> {
            //跳转至文件管理器选择文件
        });

        //迁移搜索的点击事件
        binding.btnMigrateSearchMain.setOnClickListener( v-> {
            //
            start(new MigrationSearchFragment());
        });

        //左上角头像的点击事件
        binding.viewAvatarMain.setOnClickListener( v -> {
            //测试跳转设置为跳转为地图页
            start(new MapFragment());
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        ToastUtils.showShort(TAG + "触发了返回键事件,退出APP" );
        //主页面返回键触发退出App
        return super.onBackPressedSupport();
    }



}
