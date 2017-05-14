package com.example.daniel.smartfest;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.smartfest.com.example.daniel.smartfest.services.StaticDataService;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.data;
public class FirstPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        ImageView clickAici = (ImageView) findViewById(R.id.clickAici);
        ImageView account = (ImageView) findViewById(R.id.account);
        ImageView balanta = (ImageView) findViewById(R.id.Balanta);
        ImageView plateste = (ImageView) findViewById(R.id.plateste);

        plateste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plateste = new Intent(FirstPage.this, PaymentMethod.class);
                startActivity(plateste);
            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(FirstPage.this, AccountInfo.class);
                startActivity(account);
            }
        });


        balanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balanta = new Intent(FirstPage.this, Balance.class);
                startActivity(balanta);
            }
        });

        final Activity activity = this;

        clickAici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.total);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public int scor = 0, scor1=1;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            // adaugi in lista ce trebuie, fara sa stearga din lista.
            StaticDataService.List.add(result.getContents());
            Collections.reverse(StaticDataService.List);
            String numar= result.getContents();
            if ( numar.equals("Cola") ) {
                displayPrice(scor1 * 5+scor);
                scor=scor1*5;
            }

            if ( numar.equals("Fanta") ) {
                displayPrice(scor1 * 4+scor);
                scor=scor1*4+scor;
            }

            if ( numar.equals("Tuborg") ) {
                displayPrice(scor1 * 7+scor);
                scor=scor1*7+scor;
            }
            if(numar.equals("Cola") && numar.equals("Fanta") && numar.equals("Tuborg")){
                int i=0;
                i++;
            }else
            {
                Toast.makeText(this, "Nu exista acest produs", Toast.LENGTH_SHORT).show();
            }


            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Ai anulat scanarea", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }




        }

    }
}
