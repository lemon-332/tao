package com.tao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtils {
    public static <T> T convertJson2Object(String json,Class<T> clazz){
        return JSONObject.parseObject(json,clazz);
    }
    public static String convertObject2Json(Object obj){
        return JSON.toJSONString(obj);
    }
    public static <T> List<T> convertJsonArray2List(String json,Class<T> clazz){
        return JSONArray.parseArray(json,clazz);
    }
}
