package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.databinding.FragmentTrackSearchBinding;

public class TrackSearchFragment extends BaseFragment {

    private static final String TAG = "TrackSearchFragment";
    private FragmentTrackSearchBinding fragmentTrackSearchBinding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTrackSearchBinding = FragmentTrackSearchBinding.inflate(inflater, container,false);
        return fragmentTrackSearchBinding.getRoot();
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
        fragmentTrackSearchBinding.tvTrackBack.setOnClickListener(view -> {
            //返回上一级搜索结果界面

        });
        //监听输入的搜索内容
        fragmentTrackSearchBinding.edtSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //隐藏列表，显示加载条
                fragmentTrackSearchBinding.tvLoading.setVisibility(View.VISIBLE);
                fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //隐藏加载条，显示列表
                fragmentTrackSearchBinding.tvLoading.setVisibility(View.GONE);
                fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.VISIBLE);
            }
        });
        fragmentTrackSearchBinding.edtSearchKeyword.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER&& keyEvent.getAction() == KeyEvent.ACTION_UP){
                Log.e(TAG, "onKey: 按下回车键");
                //进行搜索
                return true;
            }
            return false;
        });
    }
}
