package com.demoPack.code;


/**
 * @author Q-p
 * 宅急送接口响应代码
 * S开头的都表示成功
 * E开头的都表示失败
 */
public enum ZjsApiCodes {

    /**
     * 请求成功
     */
    SUCCESS_000("S00000","成功"),

    /**
     * 失败
     */
    ERROR_000("E00000","处理失败"),

    /**
     * 解密失败
     */
    ERROR_001("E00001","解密失败"),

    /**
     * 缺失必要的安全参数
     */
    ERROR_003("E00003","缺失必要的安全参数"),

    /**
     * 请求头解析失败
     */
    ERROR_004("E00004","请求头解析失败"),

    /**
     * 请求体解析失败
     */
    ERROR_005("E00005","请求体解析失败"),

    /**
     * 网关系统异常
     */
    ERROR_999("E00999","网关系统异常");



    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误描述信息
     */
    private String message;
    private ZjsApiCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
