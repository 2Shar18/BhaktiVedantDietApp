package com.example.user.bhaktivedantdietapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<Menu> menuList;
    private static RecyclerView menuItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
//        public RecyclerView rvMenuItem;
        Spinner spMealTime;
        private MenuItemAdapter menuItemAdapter;

        public MyViewHolder(View view){
            super(view);
            Context context = itemView.getContext();
            spMealTime = (Spinner) view.findViewById(R.id.spMealTime);
            menuItemList = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            menuItemList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            menuItemAdapter = new MenuItemAdapter();
            menuItemList.setAdapter(menuItemAdapter);
            Log.i("MA","Created View Holder of menu");
        }
    }
    public MenuAdapter(Context context,List<Menu> menuList){
        mContext = context;
        if (menuList != null){
            this.menuList = new ArrayList<>(menuList);
        }
        else this.menuList = new ArrayList<>();
        Log.i("MA","Created Menu Adapter");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.menu_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.menuItemAdapter.setData(this.menuList.get(position).getMenuItems());
        holder.menuItemAdapter.setRowIndex(position);
        Menu menu = menuList.get(position);
        Log.i("RV",menu.getTime());
        String[] some_array = holder.itemView.getResources().getStringArray(R.array.meal_time_array);
        for (int i = 0; i < some_array.length; i++){
            if (some_array[i].equals(menu.getTime())){
                holder.spMealTime.setSelection(i);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
