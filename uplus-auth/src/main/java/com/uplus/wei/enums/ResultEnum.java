package com.uplus.wei.enums;

import java.util.Objects;

/**
 * @Description: 返回结果枚举
 * @Author: chongzi
 * @Date: 2018/7/18 上午12:08
 */
public enum ResultEnum {

    SUCCESS(0, "成功"),
    ERROR(500, "系统异常,请重新再试"),
    ERROR_UNKNOWN(-1, "未知错误"),
    ERROR_DATABASE(-2, "数据库异常"),
    ERROR_DUBBO(-3, "dubbo连接异常"),
    PARAM_EMPTY(20001, "必要参数为空"),
    NO_RIGHT(20002, "无操作权限"),
    BRANCH_NOT_EXIST(20101, "网点不存在"),
    HQ_BRANCH_NOT_EXIST(20103, "主网点不存在"),
    BANNER_NOT_EXIST(20102, "轮播图不存在或已被删除"),
    ACCOUNT_MANAGER_NOT_EXIST(20104, "经理不存在"),
    IMAGE_SAVING_ERROR(20202, "图片保存失败"),
    USER_HAS_NO_BRANCH(30001, "用户未绑定微银行"),
    USER_HAS_NO_EXIST(30002, "用户不存在"),
    IMAGE_ERROR(20201, "图片格式异常"),
    PHONE_NUMBER_ALREADY_USED(30003, "手机号已被注册"),
    VALID_CODE_CHECK_ERROR(30004, "验证码错误或已过期"),
    VALID_CODE_SEND_ERROR(30005, "验证码发送失败"),
    PHONE_NUMBER_ERROR(30007, "手机号格式错误"),
    PHONE_NUMBER_NO_LIMIT_ERROR(30008, "手机号非法"),
    USER_HAS_NO_ACCOUNT_MANAGER(30006, "用户未绑定客户经理"),
    MANAGER_GIFT_PASSWORD_ERROR(30009, "客户经理赠送密码不正确"),
    MANAGER_GIFT_RAWPASSWORD_ERROR(30010, "客户经理初始赠送密码不正确"),
    MANAGER_GIFT_HAS_NO_QUOTA(30011, "客户经理没有赠送额度"),
    WEIXIN_JSDK_SIGN_EXCEPTION(40001, "微信签名异常"),
    WEIXIN_CONFIG_EXCEPTION(40002, "微信配置异常"),
    APPOINT_SCHEDULE_NOT_EXIST(50000,"该客户经理暂时没有预约排期，请重新选择客户经理预约"),
    PARAM_ERROR(1, "必要参数为空"),
    SAVE_ERROR(2,"保存失败");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getDescByValue(Integer value) {
        if(value == null){ return null;}
        String result = null;
        for (ResultEnum s : ResultEnum.values()) {
            if (Objects.equals(value,s.getCode())) {
                result = s.getMessage();
                break;
            }
        }
        return result;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
