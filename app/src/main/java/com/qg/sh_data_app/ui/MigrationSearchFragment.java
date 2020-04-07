package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.databinding.FragmentMigrationSearchBinding;

public class MigrationSearchFragment extends BaseFragment {

    private FragmentMigrationSearchBinding fragmentMigrationSearchBinding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMigrationSearchBinding= FragmentMigrationSearchBinding.inflate(inflater, container,false);
        return fragmentMigrationSearchBinding.getRoot();

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
    public void configStatusBar() {

    }

    @Override
    public void initViews() {
        fragmentMigrationSearchBinding.tvBack.setOnClickListener(view -> {
            //返回主界面

        });
        fragmentMigrationSearchBinding.tvNumber.setOnClickListener(view -> {
            //选择查看天数

            //根据选择改变界面
            if(!fragmentMigrationSearchBinding.tvNumber.getText().toString().equals("一天")){
               fragmentMigrationSearchBinding.llTwoDay.setVisibility(View.VISIBLE);
               fragmentMigrationSearchBinding.rlOneDay.setVisibility(View.GONE);
            }
        });
        fragmentMigrationSearchBinding.tvOneTime.setOnClickListener(view -> {
            //一天：选择时间

        });
        fragmentMigrationSearchBinding.tvStartTime.setOnClickListener(view -> {
            //两天及以上：选择开始时间

        });
        fragmentMigrationSearchBinding.tvEndTime.setOnClickListener(view -> {
            //两天及以上：选择结束时间

        });
        fragmentMigrationSearchBinding.btnSearch.setOnClickListener(view -> {
            //点击搜索,传相关数据给下一个界面
            start(new TwoOrMoreFragment());
            if(fragmentMigrationSearchBinding.tvNumber.getText().toString().equals("一天")){
                //获取选择的时间
                String time = fragmentMigrationSearchBinding.tvOneTime.getText().toString();
                //跳转热力图界面

            }else {
                //获取选择的时间
                String startTime = fragmentMigrationSearchBinding.tvStartTime.getText().toString();
                String endTime = fragmentMigrationSearchBinding.tvEndTime.getText().toString();
                //跳转迁移轨迹页面
                start(new TwoOrMoreFragment());
            }
        });
    }
}
