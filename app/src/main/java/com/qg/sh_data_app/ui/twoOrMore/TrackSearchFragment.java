package com.qg.sh_data_app.ui.twoOrMore;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.Constants;
import com.qg.sh_data_app.core.bean.SearchOneStuInfo;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentTrackSearchBinding;
import com.qg.sh_data_app.ui.map.MapFragment;
import com.qg.sh_data_app.util.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    private MigrationStuAdapter adapter;
    ArrayList<TwoOrMoreData.DataBean> dataBeanList= new ArrayList<>();

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
        initList();
        //返回上一级搜索结果界面
        fragmentTrackSearchBinding.tvTrackBack.setOnClickListener(view -> {
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
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                //隐藏加载条，显示列表
//                fragmentTrackSearchBinding.tvLoading.setVisibility(View.GONE);
//                fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.VISIBLE);
                if(fragmentTrackSearchBinding.edtSearchKeyword.getText()!=null){
                    search();
                }
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
        //item点击事件
        adapter.setOnItemClickListener(new AdapterItemClick() {
            @Override
            public void onClick(int position) {
                    LiveEventBus.get(Constants.SHOW_MIGRATE_TRACK).post(dataBeanList.get(position));
                    start(new MapFragment());
            }
        });
    }

    //初始化列表
    private void initList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fragmentTrackSearchBinding.rcvTrackSearchResult.setLayoutManager(layoutManager);
        adapter = new MigrationStuAdapter(dataBeanList);
        fragmentTrackSearchBinding.rcvTrackSearchResult.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(SearchOneStuInfo info){
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
                        if(twoOrMoreData.getData().size()==0){
                            Log.d(TAG, "onNext: ");
                            Toast.makeText(getContext(),"没有您要查找的学生或输入有误。",Toast.LENGTH_LONG).show();
                            fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.GONE);
                            fragmentTrackSearchBinding.tvHintSearch.setVisibility(View.VISIBLE);
                        }else if(twoOrMoreData.getCode().equals("1")){
                            showList(twoOrMoreData.getData());
                            fragmentTrackSearchBinding.rcvTrackSearchResult.setVisibility(View.VISIBLE);
                            fragmentTrackSearchBinding.tvHintSearch.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: 出现异常。");
                        Toast.makeText(getContext(),"网络出现异常，请稍后重试。",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //展示列表
    private void showList(List<TwoOrMoreData.DataBean> list) {
        dataBeanList.clear();
        dataBeanList.addAll(list);
        fragmentTrackSearchBinding.rcvTrackSearchResult.setAdapter(adapter);
    }

}
