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
import com.qg.sh_data_app.core.bean.SearchOneStuInfo;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentTrackSearchBinding;
import com.qg.sh_data_app.util.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TrackSearchFragment extends BaseFragment {

    private static final String TAG = "TrackSearchFragment";
    private FragmentTrackSearchBinding fragmentTrackSearchBinding = null;
    private String startTime = null;
    private String endTime = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTrackSearchBinding = FragmentTrackSearchBinding.inflate(inflater, container,false);
        EventBus.getDefault().register(this);
        return fragmentTrackSearchBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void configStatusBar() {

    }

    @Override
    public void initViews() {
        fragmentTrackSearchBinding.tvTrackBack.setOnClickListener(view -> {
            //返回上一级搜索结果界面
            pop();
        });
        //监听输入的搜索内容
        fragmentTrackSearchBinding.edtSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //隐藏列表，显示加载条
//                fragmentTrackSearchBinding.tvLoading.setVisibility(View.VISIBLE);
//                fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.GONE);
                search();
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                //隐藏加载条，显示列表
//                fragmentTrackSearchBinding.tvLoading.setVisibility(View.GONE);
//                fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.VISIBLE);
                search();
            }
        });
        //软键盘回车监听
        fragmentTrackSearchBinding.edtSearchKeyword.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER&& keyEvent.getAction() == KeyEvent.ACTION_UP){
                Log.e(TAG, "onKey: 按下回车键");
                //进行搜索
                search();
                return true;
            }
            return false;
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void getData(SearchOneStuInfo info){
        startTime = info.getStartTime();
        endTime=info.getEndTime();
    }

    //模糊搜索
    private void search(){
        SearchOneStuInfo info = new SearchOneStuInfo();
        info.setStartTime(startTime);
        info.setEndTime(endTime);
        info.setKeyword(fragmentTrackSearchBinding.edtSearchKeyword.getText().toString());
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
