package com.hust.party.common;

/**
 * 用于封装返回数据<br/>
 * fan 2018/5/13 18:49
 */
public class ReturnMessage {
    private int code;       //返回的代码，如200代表返回正确，201代表参数数据错误，202代表参数类型错误，500代表服务器错误
    private Object result;   //封装返回的对象

    public ReturnMessage(int code, Object result) {
        this.code = code;
        this.result = result;
    }

    public ReturnMessage() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}