package com.qg.sh_data_app.ui;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.leaf.library.StatusBarUtil;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.databinding.FragmentMainBinding;
import com.qg.sh_data_app.ui.Area_Situation.CitySituationFragment;
import com.qg.sh_data_app.ui.Sign_Situation.UnFinishSituationFragment;
import com.qg.sh_data_app.ui.login.LogoutFragment;


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
            start(new MigrationSearchFragment());
        });

        binding.btnSignSituationMain.setOnClickListener(v -> {
            start(new UnFinishSituationFragment());
        });

        //左上角头像的点击事件
        binding.viewAvatarMain.setOnClickListener( v -> {
            LogoutFragment targetFragment = new LogoutFragment();
//            CitySituationFragment targetFragment = new CitySituationFragment();
            Bundle bundle = new Bundle();
            // 传递分割后的用户名
            String[] strings = binding.tvWelcome.getText().toString().split(" ，");
            String userName = strings[0] ;
            bundle.putString("USERNAME", userName);
            //为接收方Fragment设置数据
            targetFragment.setArguments(bundle);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                setExitTransition(new Fade());
                targetFragment.setEnterTransition(new Fade());
                targetFragment.setSharedElementEnterTransition(new Fade());
                targetFragment.setSharedElementReturnTransition(new Fade());
                extraTransaction()
                        .addSharedElement(binding.viewAvatarMain, StringUtils.getString(R.string.share_elements_iv_avatar))
                        .start(targetFragment);
            } else {
                start(targetFragment);
            }
        });
    }



    @Override
    public boolean onBackPressedSupport() {
        //主页面返回键触发退出App
        return super.onBackPressedSupport();
    }



}
