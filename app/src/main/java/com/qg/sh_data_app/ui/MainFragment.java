package com.qg.sh_data_app.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.StringUtils;
import com.leaf.library.StatusBarUtil;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.bean.UploadResult;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentMainBinding;
import com.qg.sh_data_app.ui.login.LogoutFragment;

import java.io.File;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * @description: 主界面Fragment,功能菜单
 * @author: ODM
 * @date: 2020/4/6
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding = null;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


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
        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        //增加数据的点击事件
        binding.btnAddDataMain.setOnClickListener( v -> {
            //跳转至文件管理器选择文件
            //权限处理
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                openFileSelector();
            }
        });

        //迁移搜索的点击事件
        binding.btnMigrateSearchMain.setOnClickListener( v-> {
            //
            start(new MigrationSearchFragment());
        });

        //左上角头像的点击事件
        binding.viewAvatarMain.setOnClickListener( v -> {
            LogoutFragment fragment = new  LogoutFragment();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                setExitTransition(new Fade());
                fragment.setEnterTransition(new Fade());
                fragment.setSharedElementEnterTransition(new Fade());
                fragment.setSharedElementReturnTransition(new Fade());
                extraTransaction()
                        .addSharedElement(binding.viewAvatarMain, StringUtils.getString(R.string.share_elements_iv_avatar))
                        .start(fragment);
            } else {
                start(fragment);
            }
        });
    }



    @Override
    public boolean onBackPressedSupport() {
        //主页面返回键触发退出App
        return super.onBackPressedSupport();
    }


    // 打开系统的文件选择器
    private void openFileSelector() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        this.startActivityForResult(intent, 1);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFileSelector();
                } else {
                    Toast.makeText(getContext(), "请打开权限。", LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }
        Uri uri = data.getData();
        String path = getImagepath(uri);
        upload(path);
    }

    //获取图片真实路径
    private String getImagepath(Uri uri) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //上传文件
    private void upload(String path){
        File excel = new File(path);
        RetrofitManager.getInstance()
                .getApiService()
                .upload(excel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadResult uploadresult) {
                        if(uploadresult.getCode().equals("1")){
                            Toast.makeText(getContext(),"文件已成功上传！",LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"文件上传失败！请重新上传。",LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
