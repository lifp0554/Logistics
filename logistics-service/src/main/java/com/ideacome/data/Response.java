package com.ideacome.data;

/**
 * 第三方接口返回结果封装接口
 * 
 * @author laosan
 *
 */
public interface Response {

    /**
     * 判断第三方接口返回结果，并转化成灵犀标准结果
     * 
     * @return
     */
    public ResponseResult convert();
}
