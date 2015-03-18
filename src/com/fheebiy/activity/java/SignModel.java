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
            synchronized (SignModel.class){         //这段代码很关键，他保证了较为完美的单例模式(即不会因为加锁影响性能，也不会因为多线程问题而导致非真正的单例模式)
                if(instance == null){
                    instance = new SignModel();
                }
            }
        }
        return instance;
    }

}
