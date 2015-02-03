package com.fheebiy.exception;

/**
 * Created by Lenovo on 15-2-3.
 */
public class ServerResponseException extends Exception{

    private int status;

    private String msg;

    public ServerResponseException(int status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }
}
