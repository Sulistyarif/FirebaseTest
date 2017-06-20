package com.zakiadev.firebasetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by momo on 03/05/17.
 */

public class CustomAdapterTab3 extends BaseAdapter{

    private ArrayList<DataTab3> data;
    Context mContext;
    LayoutInflater inflater;

    public CustomAdapterTab3(ArrayList<DataTab3> data, Context context){
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public static class ViewHolder{
        TextView tv;
        Button btn;
    }

    private int lastPosisition = -1;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView)convertView.findViewById(R.id.tvHarga);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tv.setText(data.get(position).getHargaString());
        return convertView;
    }
}
