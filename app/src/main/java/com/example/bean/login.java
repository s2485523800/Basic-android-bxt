/**
 * Copyright 2023 bejson.com
 */
package com.example.bean;

import com.example.bean.Data;


/**
 * Auto-generated: 2023-11-08 13:31:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class login {

    private Data data;
    private int errorCode;
    private String errorMsg;
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "login{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}