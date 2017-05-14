package com.example.daniel.smartfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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
    private RadioButton maler;
    private RadioButton femaler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        names =(EditText)findViewById(R.id.name);
        ages =(EditText)findViewById(R.id.age);

    }

    public void save(View v)
    {
        String nameS=names.getText().toString().trim();
        String ageS=ages.getText().toString().trim();
        UserInformation userInformation = new UserInformation(nameS, ageS);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
    }


}
