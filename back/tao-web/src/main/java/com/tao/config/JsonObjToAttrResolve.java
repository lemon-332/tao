package com.tao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class JsonObjToAttrResolve extends HandlerInterceptorAdapter {

    private static final String CACHED_REQUEST_BODY_MAP_ATTR = "CACHED_REQUEST_BODY_MAP";
    private static final String CACHED_REQUEST_BODY_ATTR = "CACHED_REQUEST_BODY";

    private String getRequestBody(HttpServletRequest request) throws IOException {
        // 首先检查是否已经缓存了请求体
        String cachedBody = (String) request.getAttribute(CACHED_REQUEST_BODY_ATTR);
        if (cachedBody != null) {
            return cachedBody;
        }

        // 如果没有缓存，则读取请求体并缓存
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        cachedBody = sb.toString();
        // 将原始请求体缓存到request attributes中
        request.setAttribute(CACHED_REQUEST_BODY_ATTR, cachedBody);
        return cachedBody;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只处理content-type为application/json的请求
        String contentType = request.getContentType();
        if (contentType == null || !contentType.contains("application/json")) {
            return true;
        }

        String jsonBody = getRequestBody(request);
        if (jsonBody.isEmpty()) {
            return true;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将 JSON 字符串解析为 Map
            Map<String, Object> map = objectMapper.readValue(jsonBody, Map.class);
            // 将解析后的Map存储到request attributes中
            request.setAttribute(CACHED_REQUEST_BODY_MAP_ATTR, map);

            // 将每个键值对设置到请求属性中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            // 使用日志记录错误，而不是打印堆栈跟踪
            // 但仍然允许请求继续
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

