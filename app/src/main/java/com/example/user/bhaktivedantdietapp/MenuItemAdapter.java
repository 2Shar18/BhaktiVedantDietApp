package com.example.user.bhaktivedantdietapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MyViewHolder> {
    private List<MenuItem> menuList;
    private int mRowIndex = -1;

    public MenuItemAdapter() {
    }

    public void setData(List<MenuItem> data) {
        if (menuList != data) {
            menuList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Spinner spMealItem, spMealQuantity;

        public MyViewHolder(View view){
            super(view);
            spMealItem = (Spinner) view.findViewById(R.id.spMealItem);
            spMealQuantity = (Spinner) view.findViewById(R.id.spMealQuantity);
        }
    }
    public MenuItemAdapter(List<MenuItem> menuList){
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder rawHolder, int position) {
        MyViewHolder holder = (MyViewHolder) rawHolder;
        MenuItem menuItem = menuList.get(position);
        Log.i("RV",menuItem.getMenuItem());

        String[] some_array = holder.itemView.getResources().getStringArray(R.array.meal_item_array);
        for (int i = 0; i < some_array.length; i++){
            Log.i("RV",some_array[i]);
            if (some_array[i].equals(menuItem.getMenuItem())){
                holder.spMealItem.setSelection(i);
                break;
            }
        }

        some_array = holder.itemView.getResources().getStringArray(R.array.meal_quantity_array);
        for (int i = 0; i < some_array.length; i++){
            if (some_array[i].equals(menuItem.getMenuQuantity())){
                holder.spMealQuantity.setSelection(i);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
