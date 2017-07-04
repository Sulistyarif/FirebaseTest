package com.zakiadev.firebasetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Gndx on 3/19/2017.
 */

public class Tab2 extends Fragment {

    private TextView tvData1,tvData2,tvData3,tvData4;
    private int saldo,fsaldo;
    public String username, email, password, id;
    private String key;
    private Button btn;
    private DatabaseReference mFirebaseDatabase;
    User user = new User();


    public Tab2(){
        // empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("relation");

        username = getArguments().getString("username");
        System.out.println(username);
        email = getArguments().getString("email");
        password = getArguments().getString("password");
        saldo = getArguments().getInt("saldo");
        key = getArguments().getString("key");
        id = getArguments().getString("id");

        DatabaseReference data = mFirebaseDatabase.child(id);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Relation dataDiambil = dataSnapshot.getValue(Relation.class);
                fsaldo = dataDiambil.getSaldo();
                tvData4.setText("Saldo : " + fsaldo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
        tvData1 = (TextView)view.findViewById(R.id.tvData1);
        tvData2 = (TextView)view.findViewById(R.id.tvData2);
        tvData3 = (TextView)view.findViewById(R.id.tvData3);
        tvData4 = (TextView)view.findViewById(R.id.tvData4);

        btn = (Button)view.findViewById(R.id.btnTransaksi);

        tvData1.setText("Telepon : " + username);
        tvData2.setText("Email : " + email);
        tvData3.setText("Password : " + password);
        tvData4.setText("Saldo : " + saldo);


        // simulasi mengurangi saldo
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Seharusnya saldo anda akan berkurang..", Snackbar.LENGTH_LONG).setAction("No action",null).show();
//                int kurangSaldo = saldo - 500;
//                mFirebaseDatabase.child(key).child("saldo").setValue(kurangSaldo);
//            }
//        });

        return view;
    }
}
