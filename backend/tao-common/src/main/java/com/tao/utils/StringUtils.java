package com.tao.utils;

import java.util.UUID;

public class StringUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
