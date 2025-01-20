package com.tao.myEnum;
public enum ResponseCode {
    CODE_200(200, "成功"), CODE_404(404, "请求地址并不存在"), CODE_600(600, "请求参数错误")
    ,CODE_500(500, "服务器错误"),CODE_900(900,"http请求超时"), CODE_901(901,"登录超时");
    private Integer code;

    private String info;

    private ResponseCode(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
