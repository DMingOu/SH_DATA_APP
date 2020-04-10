package com.qg.sh_data_app.ui.twoOrMore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.qg.sh_data_app.R;
import com.qg.sh_data_app.core.Constants;
import com.qg.sh_data_app.core.bean.Migrate;
import com.qg.sh_data_app.core.bean.SingleStudentMigrateData;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.databinding.ItemStuInfoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class MigrationStuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<TwoOrMoreData.DataBean> mDataBeanList;
    private AdapterItemClick adapterItemClick;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView name;
        TextView number;
        TextView migrateNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.tv_stu_name);
            number = view.findViewById(R.id.tv_stu_number);
            migrateNumber = view.findViewById(R.id.tv_stu_migrate_number);
        }
    }

    //构造器传入数据
    public MigrationStuAdapter(List<TwoOrMoreData.DataBean> dataBeanList) {
        this.mDataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stu_info, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //子项赋值
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(mDataBeanList.get(position).getStudentName());
        viewHolder.number.setText(mDataBeanList.get(position).getStudentId());
        viewHolder.migrateNumber.setText(mDataBeanList.get(position).getMigrate().size()+"");
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterItemClick.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public void setOnItemClickListener(AdapterItemClick adapterItemClick) {
        this.adapterItemClick = adapterItemClick;
    }
}
