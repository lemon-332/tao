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

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jsonBody = getRequestBody(request);
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
            e.printStackTrace(); // 处理解析错误
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

