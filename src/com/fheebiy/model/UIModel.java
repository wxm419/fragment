package com.fheebiy.model;

import android.app.Activity;
import android.content.Context;

/**
 * Created by bob zhou on 15-1-5.
 */
public class UIModel {

    private String name;

    private  Class<?> cls;


    public UIModel(String name, Class<?> cls) {
        this.name = name;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
