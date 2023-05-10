package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Activity.ConstructionActivity;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Construction;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConstructionAdapter extends BaseAdapter {
    private List<Construction> constructionList;
    private LayoutInflater layoutInflater;
    private ConstructionActivity constructionActivity;
    private ConstructionListener ConstructionListener;

    public void setConstructionListener(ConstructionListener constructionListener){
        this.ConstructionListener = constructionListener;
    }

    public ConstructionAdapter(List<Construction> constructionList, Context context) {
        this.constructionList = constructionList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return constructionList.size();
    }

    @Override
    public Object getItem(int position) {
        return constructionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Construction construction = constructionList.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.construction_layout_listview, null);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvNumEmploy = convertView.findViewById(R.id.tvNumEmploy);
            viewHolder.tvAddress = convertView.findViewById(R.id.tvAddress);
            viewHolder.tvDateStart = convertView.findViewById(R.id.tvDateStart);
            viewHolder.imvEdit = convertView.findViewById(R.id.imvEdit);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.tvName.setText("Tên: "+construction.getTenCT());
        viewHolder.tvNumEmploy.setText("Số lượng nhân sự: "+construction.getSoLuongNS());
        viewHolder.tvAddress.setText("Địa chỉ: "+construction.getDiaChi());
        viewHolder.tvDateStart.setText("Khởi công: "+format.format(construction.getNgayKhoiCong()));
        viewHolder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructionListener.onItemClick(v, position );

            }
        });
        return convertView;
    }
    public class ViewHolder {
        TextView tvName, tvNumEmploy, tvAddress, tvDateStart;
        ImageView imvEdit;

    }
    public interface ConstructionListener{
        public void onItemClick(View view, int position);

    }

}
