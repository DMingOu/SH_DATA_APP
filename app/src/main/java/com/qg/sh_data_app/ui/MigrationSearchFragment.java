package com.qg.sh_data_app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qg.sh_data_app.base.BaseFragment;
import com.qg.sh_data_app.core.bean.SearchAllStuInfo;
import com.qg.sh_data_app.databinding.FragmentMigrationSearchBinding;
import com.qg.sh_data_app.ui.Area_Situation.CitySituationFragment;
import com.qg.sh_data_app.ui.twoOrMore.TwoOrMoreFragment;

import jsc.kit.wheel.base.WheelItem;
import jsc.kit.wheel.dialog.ColumnWheelDialog;
import jsc.kit.wheel.dialog.DateTimeWheelDialog;
import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MigrationSearchFragment extends BaseFragment {

    private FragmentMigrationSearchBinding fragmentMigrationSearchBinding = null;
    DateTimeWheelDialog oneDialog=null;
    DateTimeWheelDialog startDialog=null;
    DateTimeWheelDialog endDialog=null;
    ColumnWheelDialog dayDialog = null;
    private static final String TAG = "MigrationSearchFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMigrationSearchBinding= FragmentMigrationSearchBinding.inflate(inflater, container,false);
        return fragmentMigrationSearchBinding.getRoot();

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
        //返回主界面
        fragmentMigrationSearchBinding.tvBack.setOnClickListener(view -> {
            pop();
        });
        //选择查看天数
        fragmentMigrationSearchBinding.tvNumber.setOnClickListener(view -> {
            dayDialog = createDayDialog(fragmentMigrationSearchBinding.tvNumber);
        });
        //一天：选择时间
        fragmentMigrationSearchBinding.tvOneTime.setOnClickListener(view -> {
            oneDialog =createTimeDialog(fragmentMigrationSearchBinding.tvOneTime);
        });
        //两天及以上：选择开始时间
        fragmentMigrationSearchBinding.tvStartTime.setOnClickListener(view -> {
            startDialog =createTimeDialog(fragmentMigrationSearchBinding.tvStartTime);
        });
        //两天及以上：选择结束时间
        fragmentMigrationSearchBinding.tvEndTime.setOnClickListener(view -> {
            endDialog =createTimeDialog(fragmentMigrationSearchBinding.tvEndTime);
        });
        //点击搜索,传相关数据给下一个界面
        fragmentMigrationSearchBinding.btnSearch.setOnClickListener(view -> {
            if(fragmentMigrationSearchBinding.tvNumber.getText().toString().equals("一天")){
                if(fragmentMigrationSearchBinding.tvOneTime.getText().toString().equals("请选择时间")) {
                    Toast.makeText(getContext(),"请选择时间！",Toast.LENGTH_SHORT).show();
                }else {
                    //获取选择的时间
                    String time = fragmentMigrationSearchBinding.tvOneTime.getText().toString();
                    //跳转热力图界面
                    EventBus.getDefault().postSticky(time);
                    start(new CitySituationFragment());
                }
            }else if(fragmentMigrationSearchBinding.tvNumber.getText().toString().equals("两天或两天以上")){
                if(!fragmentMigrationSearchBinding.tvStartTime.getText().toString().equals("请选择时间") && !fragmentMigrationSearchBinding.tvEndTime.getText().toString().equals("请选择时间")){
                    //获取选择的时间
                    String startTime = fragmentMigrationSearchBinding.tvStartTime.getText().toString();
                    String endTime = fragmentMigrationSearchBinding.tvEndTime.getText().toString();
                    SearchAllStuInfo allStuInfo = new SearchAllStuInfo();
                    allStuInfo.setStartTime("2020-04-01");
                    allStuInfo.setEndTime("2020-04-08");
                    //传递数据
                    EventBus.getDefault().postSticky(allStuInfo);
                    //跳转迁移轨迹页面
                    start(new TwoOrMoreFragment());
                }else {
                    Toast.makeText(getContext(),"请选择时间！",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getContext(),"请选择天数！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //时间选择器
    private DateTimeWheelDialog createTimeDialog(TextView tvResult) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date startDate = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        Date endDate = calendar.getTime();
        DateTimeWheelDialog dialog = new DateTimeWheelDialog(getActivity());
//        dialog.setShowCount(7);
//        dialog.setItemVerticalSpace(24);
        dialog.show();
        dialog.setTitle("");
        dialog.configShowUI(DateTimeWheelDialog.SHOW_YEAR_MONTH_DAY);
        dialog.setCancelButton("取消", null);
        dialog.setOKButton("确定", new DateTimeWheelDialog.OnClickCallBack() {
            @Override
            public boolean callBack(View v, @NonNull Date selectedDate) {
                tvResult.setTextColor(Color.parseColor("#404040"));
                tvResult.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
                Log.d(TAG, selectedDate.toString());
                return false;
            }
        });
        dialog.setDateArea(startDate, endDate, true);
        //dialog.updateSelectedDate(new Date());
        return dialog;
    }

    //天数选择器
    private ColumnWheelDialog createDayDialog(TextView tvResult) {
        ColumnWheelDialog<WheelItem, WheelItem, WheelItem, WheelItem, WheelItem> dialog = new ColumnWheelDialog<>(getActivity());
        dialog.show();
        dialog.setTitle("");
        dialog.setCancelButton("取消", null);
        dialog.setOKButton("确定", new ColumnWheelDialog.OnClickCallBack<WheelItem, WheelItem, WheelItem, WheelItem, WheelItem>() {
            @Override
            public boolean callBack(View v, @Nullable WheelItem item0, @Nullable WheelItem item1, @Nullable WheelItem item2, @Nullable WheelItem item3, @Nullable WheelItem item4) {
                String result = "";
                if (item0 != null)
                    result += item0.getShowText() ;
                if (item1 != null)
                    result += item1.getShowText();
                if (item2 != null)
                    result += item2.getShowText();
                if (item3 != null)
                    result += item3.getShowText();
                if (item4 != null)
                    result += item4.getShowText();
                tvResult.setTextColor(Color.parseColor("#404040"));
                tvResult.setText(result);
                Log.d(TAG, result);
                if(result.equals("一天")){
                    fragmentMigrationSearchBinding.llTwoDay.setVisibility(View.GONE);
                    fragmentMigrationSearchBinding.rlOneDay.setVisibility(View.VISIBLE);
                    Log.d("一天", result);
                }else {
                    fragmentMigrationSearchBinding.llTwoDay.setVisibility(View.VISIBLE);
                    fragmentMigrationSearchBinding.rlOneDay.setVisibility(View.GONE);
                    Log.d("两天", result);
                }
                return false;
            }
        });
        dialog.setItems(
                initItems(),
                null,
                null,
                null,
                null
        );
        dialog.setSelected(
                new Random().nextInt(2),
                0,
                0,
                0,
                0
        );
        return dialog;
    }

    //初始化天数选择器的子项
    private WheelItem[] initItems() {
        final WheelItem[] items = new WheelItem[2];
        items[0] = new WheelItem("一天");
        items[1] = new WheelItem("两天或两天以上");
        return items;
    }
}
