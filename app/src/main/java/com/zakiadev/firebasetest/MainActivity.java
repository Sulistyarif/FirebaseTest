package com.zakiadev.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsername;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // membuat objek yang digunakan untuk mengambil data yang di pass in,
        // ketika  pemanggilan activity ini
        Intent passIntent = getIntent();

        String username = passIntent.getStringExtra("username");
        String email = passIntent.getStringExtra("email");
        String password = passIntent.getStringExtra("password");

//        System.out.println("test data username : " + username);
//        System.out.println("test data email : " + email);
//        System.out.println("test data password : " + password);

        tvUsername = (TextView)findViewById(R.id.tvAkun);
        btnLogout = (Button)findViewById(R.id.btLogout);

        tvUsername.setText("Username kamu : " + username + "\nEmail kamu : " + email + "\nPassword kamu : " + password);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(mv);
                finish();
            }
        });
    }
}
