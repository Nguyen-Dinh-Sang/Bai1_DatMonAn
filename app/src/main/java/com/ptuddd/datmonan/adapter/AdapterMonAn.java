package com.ptuddd.datmonan.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ptuddd.datmonan.R;
import com.ptuddd.datmonan.data.MonAn;

import java.util.List;

public class AdapterMonAn extends RecyclerView.Adapter<AdapterMonAn.ViewHolder> {

    private List<MonAn> monAnList;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;

    public AdapterMonAn(Context context, List<MonAn> monAnList) {
        this.mInflater = LayoutInflater.from(context);
        this.monAnList = monAnList;
        onItemClickListener = (OnItemClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_mon_an, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String tenMonAn = monAnList.get(position).getTenMonAn();
        String diaChi = monAnList.get(position).getDiaChi();
        String linkWeb = monAnList.get(position).getLinkWeb();
        int hinhAnh = monAnList.get(position).getImage();
        holder.hinhAnhMonAn.setImageResource(hinhAnh);

        holder.tenMonAn.setText(tenMonAn);
        holder.diaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onLocationClick(monAnList.get(position).getDiaChi());
            }
        });
        holder.website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onWebsiteClick(monAnList.get(position).getLinkWeb());
            }
        });
//        holder.diaChi.setT
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tenMonAn;
        ImageView diaChi,website,hinhAnhMonAn;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            tenMonAn = itemView.findViewById(R.id.tv_ten_mon_an);
            diaChi = itemView.findViewById(R.id.iv_map);
            website = itemView.findViewById(R.id.iv_search);
            checkBox = itemView.findViewById(R.id.cb_choose);
            hinhAnhMonAn = itemView.findViewById(R.id.iv_hinh_mon_an);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    monAnList.get(getAdapterPosition()).setChoose(isChecked);
                }
            });

//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//
////            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }

    public interface OnItemClickListener{
        void onLocationClick(String location);
        void onWebsiteClick(String website);
    }

}