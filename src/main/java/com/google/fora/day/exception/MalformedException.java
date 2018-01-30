package com.google.fora.day.exception;

import java.net.MalformedURLException;

public class MalformedException extends MalformedURLException {

    private final String msg;

    public MalformedException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MalformedException() {
        this("Url mal formada, por favor corrijela!!");
    }

    public String getMsg() {
        return msg;
    }
}
