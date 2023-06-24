package com.example.exp.ResponseResult;


public enum ErrorCodeEnums {

    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统异常", ResultCode.SYSTEM_ERROR),
    INTERFACE_EXCEPTION("INTERFACE_EXCEPTION", "接口异常", ResultCode.SYSTEM_ERROR),
    PARAM_VALIDATE_EXCEPTION("PARAM_VALIDATE_EXCEPTION", "参数校验异常", ResultCode.PARAM_VALIDATE_ERROR),
    USER_NOT_LOGIN_EXCEPTION("USER_NOT_LOGIN_EXCEPTION", "用户未登录", ResultCode.NOT_LOGIN_ERROR),
    USER_REPEAT_EXCEPTION("USER_REPEAT_EXCEPTION", "用户表存在重复用户信息", ResultCode.NOT_LOGIN_ERROR),
    USER_NOT_LOGINNAME_EXCEPTION("USER_NOT_LOGINNAME_EXCEPTION", "登录账号不能为空",ResultCode.NOT_LOGIN_ERROR),
    EXCEL_EXPORT_EXCEPTION("EXCEL_EXPORT_EXCEPTION", "EXCEL导出异常", ResultCode.EXCEL_OPERATE_ERROR),
    EXCEL_IMPORT_EXCEPTION("EXCEL_IMPORT_EXCEPTION", "EXCEL导入异常", ResultCode.EXCEL_OPERATE_ERROR),
    MSG_ILLEGAL_ACCESS_EXCEPTION("MSG_ILLEGAL_ACCESS_EXCEPTION", "非法访问!", ResultCode.EXCEL_OPERATE_ERROR),
    MSG_BEANS_EXCEPTION("MSG_BEANS_EXCEPTION", "bean转化异常!", ResultCode.EXCEL_OPERATE_ERROR),
    MSG_PATH_EXCEPTION("MSG_PATH_EXCEPTION", "未找到路径异常!", ResultCode.EXCEL_OPERATE_ERROR),
    WORKPROJECT_BEGINTIMEAFTERENDTIME_EXCEPTION("WORKPLAN_BEGINTIMEAFTERENDTIME_EXCEPTION", "开工时间不能大于等于竣工时间", ResultCode.EXCEL_OPERATE_ERROR),
    WORKPLAN_PHONE_EXCEPTION("WORKPLAN_PHONE_EXCEPTION", "手机号不能为空", ResultCode.EXCEL_OPERATE_ERROR),
    WORKPLAN_PHONE_WITH_PARAM_EXCEPTION("WORKPLAN_PHONE_WITH_PARAM_EXCEPTION", "{0}手机号不能为空", ResultCode.EXCEL_OPERATE_ERROR),
    WORKPLAN_SPECIALCHAR_EXCEPTION("WORKPLAN_SPECIALCHAR_EXCEPTION", "{0}必须为汉字/英文/数字,当前不合法值为:{1}", ResultCode.EXCEL_OPERATE_ERROR),
    COMMON_PHONESIZE_EXCEPTION("COMMON_PHONESIZE_EXCEPTION", "手机号码长度须为11位", ResultCode.EXCEL_OPERATE_ERROR),
    COMMON_PHONESIZE_WITH_PARAM_EXCEPTION("COMMON_PHONESIZE_WITH_PARAM_EXCEPTION", "{0}手机号码长度须为11位", ResultCode.EXCEL_OPERATE_ERROR);

    /**
     * 错误名字
     */
    private String name;

    /**
     * 错误描述
     */
    private String desc;

    /**
     * 结果编码
     */
    private int code;

    ErrorCodeEnums(String name, String desc, int code) {
        this.name = name;
        this.desc = desc;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}

