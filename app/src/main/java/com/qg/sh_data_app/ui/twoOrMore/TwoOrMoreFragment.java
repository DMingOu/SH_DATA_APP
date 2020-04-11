package com.qg.sh_data_app.ui.twoOrMore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.leaf.library.StatusBarUtil;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.Constants;
import com.qg.sh_data_app.core.bean.SearchAllStuInfo;
import com.qg.sh_data_app.core.bean.SearchOneStuInfo;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.core.net.RetrofitManager;
import com.qg.sh_data_app.databinding.FragmentTwoOrMoreBinding;
import com.qg.sh_data_app.ui.map.MapFragment;
import com.qg.sh_data_app.util.CustomClickListener;
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

public class TwoOrMoreFragment extends BaseFragment {

    private static final String TAG = "TwoOrMoreFragment";
    private FragmentTwoOrMoreBinding fragmentTwoOrMoreBinding = null;
    private MigrationStuAdapter adapter;
    ArrayList<TwoOrMoreData.DataBean> dataBeanList= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTwoOrMoreBinding = FragmentTwoOrMoreBinding.inflate(inflater, container,false);
        EventBus.getDefault().register(this);
        return fragmentTwoOrMoreBinding.getRoot();
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
        StatusBarUtil.setColor(this._mActivity, getResources().getColor(R.color.title_bar_city_situation));
        StatusBarUtil.setLightMode(_mActivity);
    }

    @Override
    public void initViews() {
        initList();
        fragmentTwoOrMoreBinding.imvBackTwo.setOnClickListener(view -> {
            //返回迁移搜索界面
            pop();
        });
        fragmentTwoOrMoreBinding.llBtnToSearch.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                //传递时间数据
                SearchOneStuInfo oneStuInfo = new SearchOneStuInfo();
                oneStuInfo.setStartTime(fragmentTwoOrMoreBinding.tvTwoStartTime.getText().toString());
                oneStuInfo.setEndTime(fragmentTwoOrMoreBinding.tvTwoEndTime.getText().toString());
                EventBus.getDefault().postSticky(oneStuInfo);
                //跳转至轨迹搜索界面
                start(new TrackSearchFragment());
            }
            @Override
            protected void onFastClick() {
                Toast.makeText(getContext(),"请勿重复点击！",Toast.LENGTH_SHORT).show();
            }
        });
        //item点击事件
        adapter.setOnItemClickListener(new AdapterItemClick() {
            @Override
            public void onClick(int position) {
                //传递数据并跳转至地图页
                LiveEventBus.get(Constants.SHOW_MIGRATE_TRACK).post(dataBeanList.get(position));
                start(new MapFragment());
            }
        });
    }

    //初始化列表
    private void initList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fragmentTwoOrMoreBinding.rcvTwoDayResult.setLayoutManager(layoutManager);
        adapter = new MigrationStuAdapter(dataBeanList);
        fragmentTwoOrMoreBinding.rcvTwoDayResult.setAdapter(adapter);
    }

    //根据上一个fragment传来的时间进行搜索
    @Subscribe(threadMode = ThreadMode.BACKGROUND,sticky = true)
    public void search(SearchAllStuInfo info){
        initTitle(info.getStartTime(),info.getEndTime());
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
                            Log.d(TAG, "onNext:1 ");
                            showList(twoOrMoreData.getData());
                        }else {
                            Toast.makeText(getContext(),"该时间段数据为空！",Toast.LENGTH_SHORT).show();
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


    //初始化标题
    private void initTitle(String start, String end){
        fragmentTwoOrMoreBinding.tvTwoStartTime.setText(start);
        fragmentTwoOrMoreBinding.tvTwoEndTime.setText(end);
    }

    //展示列表
    private void showList(List<TwoOrMoreData.DataBean> list) {
        dataBeanList.clear();
        dataBeanList.addAll(list);
        fragmentTwoOrMoreBinding.rcvTwoDayResult.setAdapter(adapter);
    }
}
