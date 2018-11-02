package com.example.user.bhaktivedantdietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.user.bhaktivedantdietapp.ConnectionClass.CONN;

public class HomePage extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        pref = getApplicationContext().getSharedPreferences("DietLogin", 0);
        String user = pref.getString("user", null);
        if (user == null) {
            finish();
        }
        TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        String name = getName(user);
        tvWelcome.setText("Welcome " + name);
    }

    public String getName(String user) {
        String name = null;
        try {

            Connection DbConn = CONN();

            Log.w("Connection", "open");
            Statement stmt = DbConn.createStatement();
            String loginQuery = "select * from Vw_Personheader where PersonCode = '" + user + "'";
            Log.i("query", loginQuery);
            ResultSet rs = stmt.executeQuery(loginQuery);
            rs.next();
            name = rs.getString("PersonFullName");
            DbConn.close();
            return name;

        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }
        return name;
    }

    public void logout(View view) {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        finish();
    }

    public void selectByFloor(View view) {
        startActivity(new Intent(HomePage.this, Floor.class));
    }

    public void getPatient(View view) {
        EditText etIPC = (EditText) findViewById(R.id.etIPC);
        String ipc = etIPC.getText().toString().toLowerCase();

        try {
            Connection DbConn = CONN();
            Log.w("Connection", "open");
            Statement stmt = DbConn.createStatement();
            String bedQ = "select count(*) as c from vw_BedPersonDetail where PatientIpCaseNumber = '" + ipc + "'";
            ResultSet rs = stmt.executeQuery(bedQ);
            rs.next();
            if (rs.getInt("c") == 1){
                bedQ = "select * from vw_BedPersonDetail where PatientIpCaseNumber = '" + ipc + "'";
                rs = stmt.executeQuery(bedQ);
                rs.next();
                String bed = rs.getString("BedCode");
                Intent intent = new Intent(HomePage.this, BedDetail.class);
                intent.putExtra("bed",bed);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Patient Discharged!", Toast.LENGTH_SHORT).show();
            }
            DbConn.close();
        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }
    }
}
