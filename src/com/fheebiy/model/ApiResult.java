package com.fheebiy.model;

/**
 * Created by Lenovo on 14-12-24.
 */
public abstract class ApiResult {

    public Head head;

    public static class Head {
        public int status;
        public boolean hasMore;
    }


}
