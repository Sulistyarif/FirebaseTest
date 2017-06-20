package com.zakiadev.firebasetest;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gndx on 3/19/2017.
 */
public class ListviewHistoryAdapter extends BaseAdapter {

    private static ArrayList<Tab1.ListviewHistory> listHistory;

    private LayoutInflater mInflater;

    public ListviewHistoryAdapter(Context tab1, ArrayList<Tab1.ListviewHistory> result){
        listHistory = result;
        mInflater = LayoutInflater.from(tab1);
    }

    @Override
    public int getCount() {
        return listHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return listHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        TextView txtHalte,txtTanggal,txtJam;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_history,null);
            holder = new ViewHolder();
            holder.txtHalte = (TextView)convertView.findViewById(R.id.textView5);
            holder.txtTanggal = (TextView)convertView.findViewById(R.id.textView6);
            holder.txtJam = (TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtHalte.setText(listHistory.get(position).getHalte());
        holder.txtTanggal.setText(listHistory.get(position).getTanggal());
        holder.txtJam.setText(listHistory.get(position).getJam());

        return convertView;
    }

//    public void eventClick(View v){
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ListviewHistoryAdapter);
//
//    }


}
