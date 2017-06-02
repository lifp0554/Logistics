package com.ideacome.data;

/**
 * 第三方接口返回后进行结果校验后封装的类
 * 
 * @author laosan
 *
 */
public class ResponseResult {

    private int id;
    
    private int code;
    
    private String msg;

    public ResponseResult(int id, int code, String msg) {
        super();
        this.id = id;
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
