package com.qg.sh_data_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseActivity;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author odm
 */
public class ContainerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        // 加载根Fragment
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_fragments_container, new MainFragment());
        }
    }

    /**
     * 设置Fragment的抓场动画
     * @return 转场动画
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        // return new DefaultHorizontalAnimator();
        // 设置无动画
        // return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);
        // 默认竖向(和安卓5.0以上的动画相同)
        //  return super.onCreateFragmentAnimator();

        return new DefaultHorizontalAnimator();
    }
}
