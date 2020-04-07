package com.qg.sh_data_app.base;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.qg.sh_data_app.R;

import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;

/**
 * @description: 基类Activity
 * @author: ODM
 * @date: 2020/4/5
 */
public class BaseActivity extends SupportActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 屏幕适配，宽度适配，百分比方案
     * @return 修改后的资源
     */
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 750);
    }





}
