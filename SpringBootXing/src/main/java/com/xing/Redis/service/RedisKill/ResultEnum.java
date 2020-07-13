package com.xing.Redis.service.RedisKill;

public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车不能为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    ORDER_CANCLE_SUCCESS(20, "订单取消成功"),
    ORDER_FINISH_SUCCESS(21, "订单完结成功"),
    PRODUCT_STATUS_ERROR(22, "商品状态不正确"),
    LOGIN_FAIL(23, "登录失败，登录信息不正确"),
    LOGOUT_SUCCESS(24, "登出成功"),
    USER_ERROR(25, "创建失败"),
    USER_EXITS(26, "用户已存在"),
    USER_NOT_EXITS(27, "用户不存在"),
    ORDER_FORM(28, "订单信息错错误"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
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
