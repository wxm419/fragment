package com.fheebiy.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 15-3-4.
 */
public class JsModel implements Serializable{

    private String category;

    private int index;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
