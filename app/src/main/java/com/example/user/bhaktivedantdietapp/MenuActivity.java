package com.example.user.bhaktivedantdietapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();

        recyclerView = (RecyclerView) findViewById(R.id.rvMenu);
        prepareMenuData();
        menuAdapter = new MenuAdapter(getApplicationContext(),menuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);
    }

    private void prepareMenuData(){

        List<MenuItem> list = new ArrayList<>(),list1 = new ArrayList<>();
        MenuItem menuItem = new MenuItem("Chai", "One");
        list.add(menuItem);
        list1.add(menuItem);
        Menu menu = new Menu("All",list);
        menuList.add(menu);
        menuItem = new MenuItem("Rice","Two");
        list.add(menuItem);
        list1.add(menuItem);
        menuItem = new MenuItem("Dal","One and Half");
        list1.add(menuItem);
        menu = new Menu("Morning",list1);
        menuList.add(menu);
        Log.i("MA",menuList.toString());
    }
}
