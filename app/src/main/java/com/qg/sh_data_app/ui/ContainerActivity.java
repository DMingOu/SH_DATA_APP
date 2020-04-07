package com.qg.sh_data_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseActivity;

/**
 * @author odm
 */
public class ContainerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        // 加载根Fragment
        // 如果未登录则加载登录页，再由登录页进入主页面
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_fragments_container, new MainFragment());
        }
    }
}
