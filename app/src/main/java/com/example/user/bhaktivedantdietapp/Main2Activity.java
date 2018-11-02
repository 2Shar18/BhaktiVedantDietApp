package com.example.user.bhaktivedantdietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.user.bhaktivedantdietapp.ConnectionClass.CONN;

public class Main2Activity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        boolean done = alreadyLogged();
        if (done) {
            startActivity(new Intent(Main2Activity.this, HomePage.class));
            finish();
        }

        btn = (Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = (EditText) findViewById(R.id.uid);
                EditText pass = (EditText) findViewById(R.id.pass);

                if(id.getText().length() == 0 || pass.getText().length() == 0){
                    Toast.makeText(Main2Activity.this, "Please Enter the username and pass", Toast.LENGTH_SHORT).show();
                }
                else if (id.getText() == pass.getText()){
                    Toast.makeText(Main2Activity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (checkLogin(id.getText().toString()))
                        startActivity(new Intent(Main2Activity.this, HomePage.class));
                    else
                        Toast.makeText(Main2Activity.this, "Invalid ID!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean alreadyLogged(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("DietLogin", 0);
        String name =pref.getString("user",null);
        return name != null;
    }

    public boolean checkLogin(String user){
        try {

            Connection DbConn = CONN();

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();
            String loginQuery = "select count(*) as co from Vw_Personheader where PersonCode = '"+user+"'";
            Log.i("query",loginQuery);
            ResultSet rs = stmt.executeQuery(loginQuery);
            rs.next();
            int count = rs.getInt("co");
            Log.i("count",count+"");
            if (count == 1) {
                rs.next();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("DietLogin", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user",user);
                editor.apply();
                return true;
            }
            DbConn.close();

        } catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
        }
        return false;
    }

    public class BGThread extends AsyncTask<String, String, Boolean>{
        Connection DbConn;
        Statement stmt;
        String loginQuery;
        ResultSet rs;
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Intent intent;

        @Override
        protected void onPreExecute() {
            try {
                DbConn = CONN();
                Log.i("Info:","Connection Established!");
                stmt = DbConn.createStatement();
                pref = getApplicationContext().getSharedPreferences("DietLogin", 0);
                intent = new Intent(Main2Activity.this, HomePage.class);
            }catch (Exception e){
                Log.e("Error Connection:",e.getMessage());
            }
        }


        @Override
        protected Boolean doInBackground(String... params) {
            loginQuery = "select count(*) as co from Vw_Personheader where PersonCode = '"+params[0]+"'";

            try {
                rs = stmt.executeQuery(loginQuery);
                Log.i("Info:","Query Executed!");
                rs.next();
                int count = rs.getInt("co");
                Log.i("count", count + "");
                if (count == 1) {
                    rs.next();
                    editor = pref.edit();
                    editor.putString("user", params[0]);
                    editor.apply();
                    return true;
                }
            } catch (Exception e){
                Log.e("Error Connection:",e.getMessage());
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            if (result)
                startActivity(intent);
            else
                Log.w("False","Wrong");
        }
    }

}
