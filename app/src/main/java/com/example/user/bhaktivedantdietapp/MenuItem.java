package com.example.user.bhaktivedantdietapp;

public class MenuItem {
    private String menuItem, menuQuantity;

    public MenuItem(String menuItem, String menuQuantity) {
        this.menuItem = menuItem;
        this.menuQuantity = menuQuantity;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuQuantity() {
        return menuQuantity;
    }

    public void setMenuQuantity(String menuQuantity) {
        this.menuQuantity = menuQuantity;
    }
}
