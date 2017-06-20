package com.zakiadev.firebasetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zakiadev.firebasetest.R;

import java.util.ArrayList;

/**
 * Created by Gndx on 3/19/2017.
 */

public class Tab1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,container,false);

        ArrayList<ListviewHistory> listHistory = getListHistory();
        ListView listView = (ListView)view.findViewById(R.id.list_view_tab1);
        listView.setAdapter(new ListviewHistoryAdapter(getActivity(),listHistory));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Clicked", Snackbar.LENGTH_LONG).setAction("No action",null).show();
            }
        });

        return view;
    }

    private ArrayList<ListviewHistory> getListHistory(){
        ArrayList<ListviewHistory> historyList;

        historyList = new ArrayList<>();

        historyList.add(new ListviewHistory("Halte Giwangan","12/12/2017","10.10"));
        historyList.add(new ListviewHistory("Halte Colombo","12/12/2017","09.10"));
        historyList.add(new ListviewHistory("Halte Gramedia","12/12/2017","08.10"));

        return historyList;
    }

    public class ListviewHistory {
        String halte;
        String tanggal;
        String jam;

        public ListviewHistory(String halte, String tanggal, String jam){
            this.halte = halte;
            this.tanggal = tanggal;
            this.jam = jam;
        }

        String getHalte(){
            return halte;
        }

        String getTanggal(){
            return tanggal;
        }

        String getJam(){
            return jam;
        }

    }
}
