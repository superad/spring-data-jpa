package com.lagou.edu.vo;

/**
 * @author fgm
 * @description  返回结果
 * @date 2020-03-04
 ***/
public class Result<T> {
    private boolean success;
    private T data;
    private String errorCode;
    private String errorMsg;

    public static final String SERVER_ERROR="500";

    public static Result success(){
        Result result=new Result();
        result.setSuccess(true);
        return result;
    }
    public static <T>Result success(T data){
        Result result=new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T>Result failed(String errorMsg){
        return failed(SERVER_ERROR,errorMsg);
    }

    public static <T>Result failed(String errorCode,String errorMsg){
        Result result=new Result();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
