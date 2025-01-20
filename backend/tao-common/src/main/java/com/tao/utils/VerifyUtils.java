package com.tao.utils;

import com.tao.myEnum.VerifyRegexEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
    public static Boolean Verify(String regs,String value){
        if(StringUtils.isEmpty(value)){
            return null;
        }
        Pattern pattern = Pattern.compile(regs);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    public static Boolean Verify(VerifyRegexEnum verifyRegexEnum, String value){
        return Verify(verifyRegexEnum.getRegex(),value);
    }
}
