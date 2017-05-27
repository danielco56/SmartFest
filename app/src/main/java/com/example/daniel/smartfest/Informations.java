package com.example.daniel.smartfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Informations extends AppCompatActivity {
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    DatabaseReference dref;

    private TextView Nume;
    private TextView Varsta;
    private TextView Sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        Nume=(TextView)findViewById(R.id.nume);
        Varsta=(TextView)findViewById(R.id.varsta) ;
        Sex=(TextView)findViewById(R.id.sex) ;

        dref= FirebaseDatabase.getInstance().getReference().child(currentFirebaseUser.getUid().toString());
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map <String, String> map = (Map)dataSnapshot.getValue();;

                 String nume=map.get("name");
                 String varsta=map.get("age");
                 String sex = map.get("gen");

                Nume.setText(nume);
                Varsta.setText(varsta);
                Sex.setText(sex);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
