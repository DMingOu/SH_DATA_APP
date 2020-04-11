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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.leaf.library.StatusBarUtil;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.Constants;
import com.qg.sh_data_app.core.bean.UploadResult;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentMainBinding;
import com.qg.sh_data_app.ui.Sign_Situation.UnFinishSituationFragment;
import com.qg.sh_data_app.ui.login.LogoutFragment;
import com.qg.sh_data_app.util.CustomClickListener;
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
        //头像旁边的欢迎预言
        binding.tvWelcome.setText(SPUtils.getInstance().getString(Constants.USER_NAME,"") + " ，您好");

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        //导入数据的点击事件
        binding.btnAddDataMain.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                //跳转至文件管理器选择文件
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    MainFragment.this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    openFileSelector();
                }
            }
            @Override
            protected void onFastClick() {
                Toast.makeText(getContext(),"请勿重复点击！",Toast.LENGTH_SHORT).show();
            }
        });
        //迁移搜索的点击事件
        binding.btnMigrateSearchMain.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                start(new MigrationSearchFragment());
            }
            @Override
            protected void onFastClick() {
                Toast.makeText(getContext(),"请勿重复点击！",Toast.LENGTH_SHORT).show();
            }
        });
        //打卡情况的点击事件
        binding.btnSignSituationMain.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                start(new UnFinishSituationFragment());
            }
            @Override
            protected void onFastClick() {
                Toast.makeText(getContext(),"请勿重复点击！",Toast.LENGTH_SHORT).show();
            }
        });
        //左上角头像的点击事件
        binding.viewAvatarMain.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                LogoutFragment targetFragment = new LogoutFragment();
//            CitySituationFragment targetFragment = new CitySituationFragment();
                Bundle bundle = new Bundle();
                // 传递分割后的用户名
                String[] strings = binding.tvWelcome.getText().toString().split(" ，");
                String userName = strings[0] ;
                bundle.putString(Constants.USER_NAME, userName);
                //为接收方Fragment设置数据
                targetFragment.setArguments(bundle);
                //设置转场的动画
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
            }

            @Override
            protected void onFastClick() {
                Toast.makeText(getContext(),"请勿重复点击！",Toast.LENGTH_SHORT).show();
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
        startActivityForResult(intent, 1);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFileSelector();
                    Log.d(TAG, "onRequestPermissionsResult: 打开了权限");
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
                        Toast.makeText(getContext(),"网络出现异常，请稍后重试。",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
