package com.qg.sh_data_app.ui.twoOrMore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qg.sh_data_app.R;
import com.qg.sh_data_app.core.bean.TwoOrMoreData;
import com.qg.sh_data_app.databinding.ItemStuInfoBinding;

import java.util.List;

public class MigrationStuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<TwoOrMoreData.DataBean> mDataBeanList;
    private ItemStuInfoBinding itemStuInfoBinding;
    private AdapterItemClick adapterItemClick;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
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
        itemStuInfoBinding.tvStuName.setText(mDataBeanList.get(position).getStudentName());
        itemStuInfoBinding.tvStuNumber.setText(mDataBeanList.get(position).getStudentId());
        itemStuInfoBinding.tvStuMigrateNumber.setText(mDataBeanList.get(position).getMigrate().size());

    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public void setOnItemClickListener(AdapterItemClick adapterItemClick) {
        this.adapterItemClick = adapterItemClick;
    }
}
