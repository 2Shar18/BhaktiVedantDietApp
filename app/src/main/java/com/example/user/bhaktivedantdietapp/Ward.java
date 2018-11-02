package com.example.user.bhaktivedantdietapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.bhaktivedantdietapp.ConnectionClass.CONN;

public class Ward extends AppCompatActivity {

    TextView tv;
    ListView listDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward);

        listDetail = findViewById(R.id.listDetail);
        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String floor = bundle.getString("floor");
        tv.setText(floor);

        List<String> Wards = new ArrayList<String>();

        try {

            Connection DbConn = CONN();

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();

            String ipSelectQuery = "select distinct(Ward) from vw_BedPersonDetail where Floor = '"+floor+"'";
            ResultSet rs = stmt.executeQuery(ipSelectQuery);
            while(rs.next())
                Wards.add(rs.getString("Ward"));
            DbConn.close();

        } catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Wards);

        listDetail.setAdapter(arrayAdapter);

        listDetail.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                Intent intent = new Intent(listView.getContext(), ChargeClass.class);
                String s = (String) listDetail.getItemAtPosition(position);
                intent.putExtra("ward", s);
                intent.putExtra("floor", floor);
                listView.getContext().startActivity(intent);
            }
        });

    }
}
