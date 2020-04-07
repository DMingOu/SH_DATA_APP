package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.databinding.FragmentTwoOrMoreBinding;

public class TwoOrMoreFragment extends BaseFragment {

    private FragmentTwoOrMoreBinding fragmentTwoOrMoreBinding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTwoOrMoreBinding = FragmentTwoOrMoreBinding.inflate(inflater, container,false);
        return fragmentTwoOrMoreBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initViews() {
        fragmentTwoOrMoreBinding.imvBackTwo.setOnClickListener(view -> {
            //返回迁移搜索界面

        });
        fragmentTwoOrMoreBinding.btnGoToTrackSearch.setOnClickListener(view -> {
            //跳转至轨迹搜索界面
            start(new TrackSearchFragment());
        });
        //加载搜索结果
    }
}
