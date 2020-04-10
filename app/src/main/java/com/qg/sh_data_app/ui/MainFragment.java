package com.qg.sh_data_app.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.transition.Fade;
import android.util.Log;
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
import com.qg.sh_data_app.ui.Sign_Situation.UnFinishSituationFragment;
import com.qg.sh_data_app.ui.login.LogoutFragment;
import com.qg.sh_data_app.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                openFileSelector();
            }
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


    // 打开系统的文件选择器
    private void openFileSelector() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        //intent.setType("application/vnd.ms-excel application/x-excel");
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
        Uri uri = data.getData(); // 获取用户选择文件的URI
        String path = FileUtil.getPath(getContext(),uri);
        upload(path);
    }

    //上传文件
    private void upload(String path){
        File excel = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),excel);
        builder.addFormDataPart("file",excel.getName(),body);
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitManager.getInstance()
                .getApiService()
                .upload(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadResult uploadresult) {
                        if(uploadresult.getCode().equals("1")){
                            Toast.makeText(getContext(),"文件已成功上传！",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onNext: success");
                        }else {
                            Toast.makeText(getContext(),uploadresult.getMessage(),Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onNext: fail");
                            Log.d(TAG, uploadresult.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: fail");
                        Log.d(TAG, e.toString());
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
