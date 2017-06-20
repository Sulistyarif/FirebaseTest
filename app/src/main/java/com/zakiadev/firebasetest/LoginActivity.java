package com.zakiadev.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
//        mFirebaseDatabase2 =

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mv);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // mengambil satu rangkaian data (nama, email, password) yang sama dengan
                // yang diinputkan di editText username
                Query query = mFirebaseDatabase.orderByChild("name").equalTo(username.getText().toString());
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        // memasukkan data yang cocok ke user [0]
                        user[0] = dataSnapshot.getValue(User.class);
                        String key = dataSnapshot.getKey();
                        System.out.println("data yang diambil : " + user[0].getName() + " ," + user[0].getEmail() + " ," + user[0].getPaswot());

                        if (user[0].getPaswot().equals(password.getText().toString())){
                            tvUserName.setText(user[0].getName() + " ," + user[0].getEmail() + " ," + user[0].getPaswot() + " ," + user[0].getSaldo()
                                    + "\nLogin sukses");
                            Intent mv = new Intent(LoginActivity.this, FragmentedActivity.class);
                            // passing variabel agar activity selanjutnya bisa menggunakan variabelnya juga
                            mv.putExtra("username",user[0].getName());
                            mv.putExtra("email",user[0].getEmail());
                            mv.putExtra("password",user[0].getPaswot());
                            mv.putExtra("saldo",user[0].getSaldo());
                            mv.putExtra("key",key);

                            // add some test code

//                            Bundle bundle = new Bundle();
//                            bundle.putString("username", user[0].getName());
//                            bundle.putString("email", user[0].getEmail());
//                            bundle.putString("password", user[0].getPaswot());
//                            bundle.putInt("saldo", user[0].getSaldo());

//                            Tab2 tab2 = new Tab2();
//                            tab2.setArguments(bundle);

                            startActivity(mv);
//                            finish();

                        }else{
                            tvUserName.setText(user[0].getName() + " ," + user[0].getEmail() + " ," + user[0].getPaswot() + user[0].getSaldo()
                                    + "\nLogin gagal" + "\nInput dari user : \n" + username.getText().toString() + "\n" + password.getText().toString());
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                    // ketika nambah command di onChildAdded dan di onChildChanged,
                    // yang ditampilkan hanya onChildAdded
                });



            }
        });

    }
}
