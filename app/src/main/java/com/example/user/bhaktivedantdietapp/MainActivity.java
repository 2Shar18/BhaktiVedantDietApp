package com.example.user.bhaktivedantdietapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run(){
                Intent i = new Intent(MainActivity.this, Main2Activity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 3*1000); // wait for 5 seconds
    }
}
