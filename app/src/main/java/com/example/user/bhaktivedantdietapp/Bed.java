package com.example.user.bhaktivedantdietapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.bhaktivedantdietapp.ConnectionClass.CONN;

public class Bed extends AppCompatActivity {

    TextView tv;
    ListView listDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed);
        Bundle bundle = getIntent().getExtras();

        final String floor = bundle.getString("floor");
        final String ward = bundle.getString("ward");
        final String clas = bundle.getString("class");
        TextView tv = (TextView) findViewById(R.id.tv);
        listDetail = findViewById(R.id.listdetail);

        tv.setText(floor+">"+ward+">"+clas);
        List<String> Beds = new ArrayList<String>();
        try {

            Connection DbConn = CONN();

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();

            String ipSelectQuery = "select BedCode from vw_BedPersonDetail where Floor = '"+floor+"' and Ward = '"+ward+"' and Class='"+clas+"' order by BedCode";
            ResultSet rs = stmt.executeQuery(ipSelectQuery);
            while(rs.next())
                Beds.add(rs.getString("BedCode"));
            DbConn.close();

        } catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Beds);

        listDetail.setAdapter(arrayAdapter);

        listDetail.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                Intent intent = new Intent(listView.getContext(), BedDetail.class);
                String s = (String) listDetail.getItemAtPosition(position);
                intent.putExtra("bed", s);
                intent.putExtra("class", clas);
                intent.putExtra("floor", floor);
                intent.putExtra("ward", ward);
                listView.getContext().startActivity(intent);
            }
        });
//        final LinearLayout lm = (LinearLayout) findViewById(R.id.ly);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        for(int i=0;i<4;i++) {
//            LinearLayout ll = new LinearLayout(this);
//            Button b = new Button(this);
//            b.setId(i);
//            b.setText("Hello");
//            b.setTextSize(15);//Text Size
//            b.setPadding(5, 5, 5, 5);
//            b.setLayoutParams(params);
//            ll.addView(b);
//            //Add button to LinearLayout defined in XML
//            lm.addView(ll);
//        }
        /*listDetail = findViewById(R.id.listDetail);
        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String selected = bundle.getString("clas");
        tv.setText(selected);*/
    }
}
