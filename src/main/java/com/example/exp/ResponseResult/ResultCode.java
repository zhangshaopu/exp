package com.example.exp.ResponseResult;


public interface ResultCode {

    /**
     * 成功
     */
    int SUCCESS = 0;

    /**
     * 系统异常
     */
    int SYSTEM_ERROR = 1;

    /**
     * 参数校验失败
     */
    int PARAM_VALIDATE_ERROR = 2;

    /**
     * 用户未登录
     */
    int NOT_LOGIN_ERROR = 3;

    /**
     * 数据库操作失败
     */
    int DB_OPERATE_ERROR = 4;

    /**
     * Excel操作失败
     */
    int EXCEL_OPERATE_ERROR = 5;

    /**
     * Redis操作失败
     */
    int REDIS_OPERATE_ERROR = 6;

}