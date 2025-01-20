package com.tao.myEnum;

public enum VerifyRegexEnum {
    NO("","不校验"),
    IP("((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))","Ip地址"),
    NUMBER_LETTER_UNDER_LINE("^\\w+$ 或 ^\\w{3,20}$","由数字、26个英文字母或者下划线组成的字符串"),
    EMAIL("/^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$/\n","邮箱"),
    PHONE("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$","手机号码"),
    COMMON("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$","中文、英文、数字包括下划线"),
    PASSWORD("^[a-zA-Z]\\w{5,17}$","密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)"),
    ACCOUNT("^[a-zA-Z][a-zA-Z0-9_]{4,15}$","帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)"),
    ;
    private String regex;
    private String desc;

    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }

    public String getRegex() {
        return regex;
    }

    public String getDesc() {
        return desc;
    }
}
