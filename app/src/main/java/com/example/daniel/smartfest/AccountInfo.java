package com.example.daniel.smartfest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.daniel.smartfest.com.example.daniel.smartfest.services.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountInfo extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText names;
    private EditText ages;
    public RadioGroup rg;
    public RadioButton gen;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        progressDialog =new ProgressDialog(this);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        names =(EditText)findViewById(R.id.name);
        ages =(EditText)findViewById(R.id.age);
        rg=(RadioGroup)findViewById(R.id.rgrup);

    }

    public void rbCLICK(View v)
    {
        int radioButtonId=rg.getCheckedRadioButtonId();
        gen=(RadioButton)findViewById(radioButtonId);

    }


    public void save(View v)
    {
        String nameS=names.getText().toString().trim();
        String ageS=ages.getText().toString().trim();
        String genS=gen.getText().toString().trim();
        UserInformation userInformation = new UserInformation(nameS, ageS, genS);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);

        final ProgressDialog progressDialog= ProgressDialog.show(AccountInfo.this,"Please wait...", "Processing...", true);

        Toast.makeText(AccountInfo.this,"Registration successful!",Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        Intent log=new Intent(AccountInfo.this, MainActivity.class);
        startActivity(log);

    }


}
