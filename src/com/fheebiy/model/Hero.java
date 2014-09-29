package com.fheebiy.model;

import com.fheebiy.view.MySlideView;

import java.io.Serializable;

/**
 * Created by Administrator on 14-8-8.
 */
public class Hero implements Serializable{


    private String name;


    private String skill;

    private String from;

    private MySlideView mySlideView;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public MySlideView getMySlideView() {
        return mySlideView;
    }

    public void setMySlideView(MySlideView mySlideView) {
        this.mySlideView = mySlideView;
    }
}
