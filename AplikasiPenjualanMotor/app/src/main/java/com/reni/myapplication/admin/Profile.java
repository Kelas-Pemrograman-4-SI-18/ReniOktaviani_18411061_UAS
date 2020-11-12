package com.reni.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reni.myapplication.R;
import com.reni.myapplication.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView txtuserName, txtnamaLengkap, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile User");

        txtuserName = (TextView) findViewById(R.id.userName);
        txtnamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        txtemail = (TextView) findViewById(R.id.email);


        txtuserName.setText(PrefSetting.userName);
        txtnamaLengkap.setText(PrefSetting.namaLengkap);
        txtemail.setText(PrefSetting.email);

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }
}