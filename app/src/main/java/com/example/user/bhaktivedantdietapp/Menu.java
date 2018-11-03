package com.example.user.bhaktivedantdietapp;

import java.util.List;

public class Menu {
    private String time;
    private List<MenuItem> menuItems;

    public Menu(String time, List<MenuItem> menuItems) {
        this.time = time;
        this.menuItems = menuItems;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
