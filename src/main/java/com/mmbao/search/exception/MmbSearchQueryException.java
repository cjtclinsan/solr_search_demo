package com.mmbao.search.exception;

/**
 * Created by Administrator on 2017/11/21.
 */
public class MmbSearchQueryException extends Exception {
    public MmbSearchQueryException(String msg){
        super(msg);
    }

    public MmbSearchQueryException(Throwable ex){
        super(ex);
    }
}
