package com.zakiadev.firebasetest;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Gndx on 3/19/2017.
 */

public class Tab3 extends Fragment {

    private ListView listView;
    private String[] harga;
    ArrayList<DataTab3> data;
    private static CustomAdapterTab3 adapterTab3;
    private DatabaseReference mFirebaseDatabase;
    public int saldo;
    public String key;
    int tambahSaldo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab3,container,false);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("users");

        listView = (ListView)view.findViewById(R.id.list_view_tab3);

        data = new ArrayList<>();

        data.add(new DataTab3("Rp2000,00",2000));
        data.add(new DataTab3("Rp4000,00",4000));
        data.add(new DataTab3("Rp6000,00",6000));

//        saldo = getArguments().getInt("saldo");
        key = getArguments().getString("key");

        mFirebaseDatabase.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User saldoUser = dataSnapshot.getValue(User.class);
//                tambahSaldo = saldoUser.getSaldo() + dataClick.getHarga();
                saldo = saldoUser.getSaldo();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapterTab3 = new CustomAdapterTab3(data,getContext());
        listView.setAdapter(adapterTab3);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final DataTab3 dataClick = data.get(position);
//                Snackbar.make(view, dataClick.getHargaString(), Snackbar.LENGTH_LONG).setAction("No action",null).show();
                AlertDialog.Builder alertBuild = new AlertDialog.Builder(getContext());
                alertBuild.setCancelable(true);
                alertBuild.setMessage("Membeli Saldo sebesar " + dataClick.getHargaString() +
                        " akan menambah saldo sebesar" + dataClick.getHargaString() +
                        " dan akan mengurangi saldo pulsa anda sebesar" + dataClick.getHargaString());
                alertBuild.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(view, "Tidak jadi beli saldo " + dataClick.getHargaString(), Snackbar.LENGTH_LONG).setAction("No action",null).show();
                    }
                });
                alertBuild.setNegativeButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(view, "Saldo anda bertambah sebesar " + dataClick.getHargaString(), Snackbar.LENGTH_LONG).setAction("No action",null).show();
                        tambahSaldo = saldo + dataClick.getHarga();
                        mFirebaseDatabase.child(key).child("saldo").setValue(tambahSaldo);
                    }
                });

                AlertDialog alertDialog = alertBuild.create();
                alertDialog.show();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
