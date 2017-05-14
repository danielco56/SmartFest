package com.example.daniel.smartfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.daniel.smartfest.com.example.daniel.smartfest.services.StaticDataService;

import java.util.ArrayList;

public class Balance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
//
        ArrayList<String> listFromFirstPage = StaticDataService.List;
        // process list

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listFromFirstPage);

        GridView listView = (GridView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }
}
