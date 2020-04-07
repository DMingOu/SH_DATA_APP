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
        if (findFragment(TrackSearchFragment.class) == null) {
            loadRootFragment(R.id.fl_fragments_container, new TrackSearchFragment());
        }
    }
}
