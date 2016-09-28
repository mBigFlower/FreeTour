package com.flowerfat.initapp.model;

import java.util.HashMap;

/**
 * Created by 明明大美女 on 2016/9/27.
 */

public class TourSettingItem {
    String title;
    HashMap<String, Boolean> items = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Boolean> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Boolean> items) {
        this.items = items;
    }
}
