package com.example.daniel.smartfest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TextView reg=(TextView)findViewById(R.id.register) ;
        Button login= (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(MainActivity.this, FirstPage.class);
                startActivity(login);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
        EditText userName = (EditText) findViewById(R.id.username);
        EditText passWord = (EditText) findViewById(R.id.password);






    }
}
