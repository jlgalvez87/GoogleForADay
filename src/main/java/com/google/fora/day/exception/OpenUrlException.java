package com.google.fora.day.exception;

import java.io.IOException;

public class OpenUrlException extends IOException {

    private String exceptionMsg;

    public OpenUrlException(String exceptionMsg) {
        super(exceptionMsg);
        this.exceptionMsg = exceptionMsg;
    }

    public OpenUrlException() {
        this("Error de Conexion, corrija los parametros del servidor proxy en el archivo classpath:config.properties !!");
    }

    public String getExceptionMsg(){
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
