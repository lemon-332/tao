package com.tao.aspect;

import com.tao.annotation.GlobalInterceptor;
import com.tao.annotation.VerifyParam;
import com.tao.exception.BusinessException;
import com.tao.myEnum.ResponseCode;
import com.tao.utils.VerifyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class OperationAspect {

    private static final String[] TYPE_BASE = {"java.lang.String", "java.lang.Integer", "java.lang.Long"};

    @Pointcut("@annotation(com.tao.annotation.GlobalInterceptor)")
    private void requestInterceptor() {

    }

    @Around("requestInterceptor()")
    public Object InterceptorDo(ProceedingJoinPoint point) {
        try {
            Object target = point.getTarget();
            Object[] args = point.getArgs();
            String methodName = point.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (interceptor == null) {
                return null;
            }
            if (interceptor.checkLogin()) {
                checkLogin();
            }
            if (interceptor.checkParams()) {
                validateParams(method, args);
            }
            return point.proceed();

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.CODE_500);
        } catch (Throwable e) {
            throw new BusinessException(ResponseCode.CODE_500);
        }
    }

    public void checkLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object userDto = session.getAttribute("userDto");
        if (userDto == null) {
            throw new BusinessException(ResponseCode.CODE_901);
        }
    }

    public void validateParams(Method m, Object[] arguments) {
        Parameter[] parameters = m.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = arguments[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            if (ArrayUtils.contains(TYPE_BASE, parameter.getParameterizedType().getTypeName())) {
                checkValue(value, verifyParam);
            } else {
                checkObjectValue(parameter, value);
            }
        }
    }

    private void checkValue(Object value, VerifyParam verifyParam) {
        boolean isEmpty = value == null || StringUtils.isEmpty(value.toString());
        int length = value == null ? 0 : value.toString().length();
        /**
         * 校验空
         */
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException(ResponseCode.CODE_600);
        }
        /**
         * 校验长度
         */
        if (!isEmpty && (verifyParam.max() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() >= length)) {
            throw new BusinessException(ResponseCode.CODE_600);
        }
        /**
         * 检验正则
         */
        if (!StringUtils.isEmpty(verifyParam.regx().getRegex()) && VerifyUtils.Verify(verifyParam.regx(), String.valueOf(value))) {
            throw new BusinessException(ResponseCode.CODE_500);
        }

    }

    private void checkObjectValue(Parameter parameter, Object obj) {
        if (obj == null) {
            return;
        }
        // 获取对象的所有字段
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 获取字段上的VerifyParam注解
            VerifyParam fieldVerify = field.getAnnotation(VerifyParam.class);
            if (fieldVerify == null) {
                continue;
            }
            // 设置字段可访问
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                // 如果字段是基本类型或String，直接校验
                if (ArrayUtils.contains(TYPE_BASE, field.getType().getName())) {
                    checkValue(value, fieldVerify);
                } else {
                    // 如果是对象，递归校验
                    checkObjectValue(parameter, value);
                }
            } catch (IllegalAccessException e) {
                throw new BusinessException(ResponseCode.CODE_500);
            }
        }
    }
}
