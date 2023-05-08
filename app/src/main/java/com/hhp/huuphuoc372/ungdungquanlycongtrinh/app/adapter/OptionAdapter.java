package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.R;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Option;

import java.util.List;
import java.util.zip.Inflater;

public class OptionAdapter extends BaseAdapter {

    private List<Option> opList;
    private LayoutInflater layoutInflater;

    public OptionAdapter(List<Option> opList, Context context) {
        this.opList = opList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return opList.size();
    }

    @Override
    public Object getItem(int position) {
        return opList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Option option = opList.get(position);
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.option_layout, null);
            viewHolder.imgOption = convertView.findViewById(R.id.imvImage);
            viewHolder.tvOption = convertView.findViewById(R.id.tvOption);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgOption.setImageResource(option.getImgOption());
        viewHolder.tvOption.setText(option.getOption());
        return convertView;
    }

    public static class ViewHolder{
        ImageView imgOption;
        TextView tvOption;

    }
}
