package com.example.daniel.smartfest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends Activity {

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TextView reg = (TextView) findViewById(R.id.register);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);

        mAuth=FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
    }

    public void login(View v)
    {

       final ProgressDialog progressDialog= ProgressDialog.show(MainActivity.this,"Please wait...", "Processing...", true);
         mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();
                       if(task.isSuccessful())
                       {
                           Toast.makeText(MainActivity.this,"Login successful!",Toast.LENGTH_LONG).show();
                           Intent log =new Intent(MainActivity.this, FirstPage.class);
                           startActivity(log);
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this,"Please try again!",Toast.LENGTH_LONG).show();
                       }
                   }
               });





    }
}
