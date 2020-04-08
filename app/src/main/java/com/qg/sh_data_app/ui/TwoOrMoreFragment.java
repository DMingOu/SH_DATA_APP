package com.qg.sh_data_app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.util.LogTime;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.bean.SearchAllStuInfo;
import com.qg.sh_data_app.core.bean.SearchOneStuInfo;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentTwoOrMoreBinding;
import com.qg.sh_data_app.util.GsonUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TwoOrMoreFragment extends BaseFragment {

    private static final String TAG = "TwoOrMoreFragment";
    private FragmentTwoOrMoreBinding fragmentTwoOrMoreBinding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTwoOrMoreBinding = FragmentTwoOrMoreBinding.inflate(inflater, container,false);
        return fragmentTwoOrMoreBinding.getRoot();
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
        fragmentTwoOrMoreBinding.imvBackTwo.setOnClickListener(view -> {
            //返回迁移搜索界面
            pop();
        });
        fragmentTwoOrMoreBinding.btnGoToTrackSearch.setOnClickListener(view -> {
            //传递时间数据

            //跳转至轨迹搜索界面
            start(new TrackSearchFragment());
        });
    }

    //根据上一个fragment传来的时间进行搜索
    public void search(String startTime, String endTime){
        SearchAllStuInfo info = new SearchAllStuInfo();
        info.setStartTime(startTime);
        info.setEndTime(endTime);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtil.GsonString(info));
        RetrofitManager.getInstance()
                .getApiService()
                .getSearchResultData(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TwoOrMoreData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TwoOrMoreData twoOrMoreData) {
                        if(twoOrMoreData.getCode().equals("1")&&twoOrMoreData.getData()!=null){
                            initList(twoOrMoreData.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: 出现异常。");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //初始化列表
    private void initList(List<TwoOrMoreData.DataBean> dataBeanList){

    }
}
