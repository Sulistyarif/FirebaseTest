package com.zakiadev.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by momo on 16/04/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private TextView tvUserName;
    private Button login, register;
    private DatabaseReference mFirebaseDatabase,mFirebaseDatabase2;
    private FirebaseDatabase mFirebaseInstance;
    String fid,fusername,femail,fpassword,fkey = "";
    int fsaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogin);
        tvUserName = (TextView)findViewById(R.id.txtUser);
        register = (Button)findViewById(R.id.btToRegister);

        final User[] user = {new User()};
        final Relation[] relation = {new Relation()};

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseDatabase2 = mFirebaseInstance.getReference("relation");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mv);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                // mengambil satu rangkaian data (nama, email, password) yang sama dengan
                // yang diinputkan di editText username

                if (username.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username dan Password Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
                } else {
                    mFirebaseDatabase.child(username.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final User dataUser = dataSnapshot.getValue(User.class);
                                if (dataUser.getName().isEmpty()){
                                    Toast.makeText(LoginActivity.this, "Tidak Terdapat Username Anda", Toast.LENGTH_SHORT).show();
                                }else{
                                    System.out.println("Didapatkan data user : " + dataUser.getName() + " " + dataUser.getEmail() + " " + dataUser.getId() +
                                    " " + dataUser.getPaswot());
                                    fid = dataUser.getId();
                                    fusername = dataUser.getName();
                                    femail = dataUser.getEmail();
                                    fpassword = dataUser.getPaswot();
                                    if (dataUser.getPaswot().equals(password.getText().toString())){
                                        mFirebaseDatabase2.child(dataUser.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Relation dataSaldo = dataSnapshot.getValue(Relation.class);
                                                System.out.println("Didapat saldo " + dataUser.getName() + " sebesar " + dataSaldo.getSaldo());
                                                fsaldo = dataSaldo.getSaldo();

                                                Intent mvLogin = new Intent(LoginActivity.this, FragmentedActivity.class);

                                                mvLogin.putExtra("username",fusername);
                                                mvLogin.putExtra("email",femail);
                                                mvLogin.putExtra("password",fpassword);
                                                mvLogin.putExtra("saldo",fsaldo);
                                                mvLogin.putExtra("key",fkey);
                                                mvLogin.putExtra("id",fid);

                                                startActivity(mvLogin);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }else{
                                        Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

    }
}
