package com.example.daniel.smartfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class AccountInfo2 extends AppCompatActivity {

    private TextView Name;
    private TextView Email;
    private TextView Age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info2);

    }
}
