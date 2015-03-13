package com.fheebiy.activity.java;

/**
 * Created by Lenovo on 15-3-5.
 */
public class SignModel {


    private static SignModel instance;

    private SignModel() {

    }

    public static  SignModel getInstance(){
        if(instance == null){
            instance = new SignModel();
        }

        return instance;
    }

}
