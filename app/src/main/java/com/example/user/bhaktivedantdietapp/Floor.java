package com.example.user.bhaktivedantdietapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Floor extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        lv = findViewById(R.id.lv);
        List<String> Floors = new ArrayList<String>();
        Floors.add("02Floor");
        Floors.add("03Floor");
        Floors.add("04Floor");
        Floors.add("05Floor");
        Floors.add("06Floor");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Floors);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
//                String floor = (String) listView.getAdapter().getItem(position);
                String s = (String) lv.getItemAtPosition(position);
                Intent intent = new Intent(listView.getContext(), Ward.class);
                intent.putExtra("floor", s);
                listView.getContext().startActivity(intent);
            }
        });
    }
}
