package com.ideacome.data;

/**
 * 灵犀接口标准 Request
 * 
 * @author laosan
 *
 */
public interface Request {

    /**
     * 判断接口传入参数是否符合该 Request 实现规范
     * 
     * @param params
     * @return
     */
    public boolean check();
    
}
