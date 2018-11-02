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

public class Wing extends AppCompatActivity {

    TextView tv;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wing);

        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String selected = bundle.getString("floor");
        tv.setText(selected);


        lv = findViewById(R.id.LV);
        List<String> Wings = new ArrayList<String>();
        switch (selected) {
            case "02Floor":
                Wings.add("Wing B");
                Wings.add("Wing C");
                break;
            case "03Floor":
                Wings.add("Wing A");
                Wings.add("Wing B");
                Wings.add("Wing C");
                Wings.add("Wing D");
                break;
            case "04Floor":
                Wings.add("Wing A");
                Wings.add("Wing B");
                Wings.add("Wing C");
                Wings.add("Wing D");
                break;
            case "05Floor":
                Wings.add("Wing C");
                break;
            case "06Floor":
                Wings.add("Wing D");
                break;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Wings);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
//                String floor = (String) listView.getAdapter().getItem(position);
                String selectedWing = (String) lv.getItemAtPosition(position);
                String pass = selected+">"+selectedWing;
                Intent intent = new Intent(listView.getContext(), Ward.class);
                intent.putExtra("wing", selectedWing);
                intent.putExtra("floor", selected);
                intent.putExtra("message", pass);
                listView.getContext().startActivity(intent);
            }
        });
    }
}
