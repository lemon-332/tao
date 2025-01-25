package com.tao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class JsonArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CACHED_REQUEST_BODY_MAP_ATTR = "CACHED_REQUEST_BODY_MAP";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 支持所有基本类型参数
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 从request attributes中获取缓存的参数Map
        Map<String, Object> paramMap = (Map<String, Object>) request.getAttribute(CACHED_REQUEST_BODY_MAP_ATTR);
        if (paramMap == null) {
            return null;
        }

        // 获取参数名并从Map中取值
        String paramName = parameter.getParameterName();
        Object value = paramMap.get(paramName);
        Class<?> paramType = parameter.getParameterType();


        if (value != null) {
            // 处理基本类型
            if (paramType == Integer.class || paramType == int.class) {
                return value instanceof Integer ? value : Integer.valueOf(value.toString());
            } else if (paramType == Long.class || paramType == long.class) {
                return value instanceof Long ? value : Long.valueOf(value.toString());
            } else if (paramType == Double.class || paramType == double.class) {
                return value instanceof Double ? value : Double.valueOf(value.toString());
            } else if (paramType == Boolean.class || paramType == boolean.class) {
                return value instanceof Boolean ? value : Boolean.valueOf(value.toString());
            } else if (paramType == String.class) {
                return value.toString();
            }
        }
        // 处理实体对象
        try {
            // 如果value是Map类型，将其转换为目标实体对象
            if (value instanceof Map) {
                return objectMapper.convertValue(paramMap, paramType);
            }
            // 如果value已经是正确的类型，直接返回
            if (paramType.isInstance(paramMap)) {
                return paramMap;
            }
            // 尝试将value转换为目标类型
            return objectMapper.convertValue(paramMap, paramType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert value to type " + paramType.getName(), e);
        }
    }
} 