package com.zakiadev.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by momo on 16/04/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText nama,email,password;
    private Button register,login;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText)findViewById(R.id.etName);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        register = (Button)findViewById(R.id.btnRegister);
//        login = (Button)findViewById(R.id.btnLogin);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String namaUser = nama.getText().toString();
                String emailUser = email.getText().toString();
                String paswot = password.getText().toString();

                if(TextUtils.isEmpty(userId)){
                    createUser(namaUser,emailUser,paswot);
                }else{
                    register.setText("Udah ada user antum..");
                }

                Intent mv = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(mv);
            }
        });

//        login.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent mv = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(mv);
//            }
//        });
    }


        private void createUser(String namaUser, String emailUser, String paswot){
            int saldoAwal = 5000;
            if (TextUtils.isEmpty(userId)){
                userId = mFirebaseDatabase.push().getKey();
            }

            // menambahkan data ke firebase dengan id = ""
            mFirebaseDatabase.child(nama.getText().toString()).setValue(new User(nama.getText().toString(), email.getText().toString(), password.getText().toString(), ""));

    }

    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.name + ", " + user.email + ", " + user.paswot);

                // Display newly updated name and email
//                txtDetails.setText(user.name + ", " + user.email + ", " + user.paswot);

                // clear edit text
//                inputEmail.setText("");
//                inputName.setText("");
//
//                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

}

