package com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity;

import java.io.Serializable;

public class GenericResponse implements Serializable {

    private String code;
    private String key;
    private String message;
    private String locale;

    public GenericResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
