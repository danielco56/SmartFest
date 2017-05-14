package com.example.daniel.smartfest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        progressDialog =new ProgressDialog(this);

        mAuth =FirebaseAuth.getInstance();

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        TextView back=(TextView)findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back= new Intent(Register.this, MainActivity.class);
                startActivity(back);
            }
        });
    }

    public void reg(View v)
    {
        final ProgressDialog progressDialog= ProgressDialog.show(Register.this,"Please wait...", "Processing...", true);
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               progressDialog.dismiss();
                                               if(task.isSuccessful())
                                               {
                                                   Toast.makeText(Register.this,"Merge FMM",Toast.LENGTH_LONG).show();
                                                   Intent log=new Intent(Register.this, MainActivity.class);
                                                   startActivity(log);
                                               }
                                               else
                                               {
                                                   Toast.makeText(Register.this,"Nu merge",Toast.LENGTH_LONG).show();
                                               }
                                           }

                                       }
                );

    }
}
