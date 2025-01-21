package com.tao.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
    public static String random16() {
        return RandomStringUtils.random(16, true, true);
    }
}
