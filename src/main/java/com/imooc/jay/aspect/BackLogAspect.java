package com.imooc.jay.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class BackLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(BackLogAspect.class);

    // com.imooc.jay.controller下所有类都打印参数
    //@Pointcut("execution(* com.imooc.jay.controller..*.*(..))")

    // 通过添加注解@BackLogParamsAnnotation打印参数
    @Pointcut("@annotation(com.imooc.jay.anotations.BackLogParamsAnnotation)")
    public void backLog(){}

    @Before("backLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + JSON.toJSONString(joinPoint.getArgs()));



//        try {
//            HttpServletRequest request =
//                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                            .getRequest();
//            String requestUrl = request.getServerName() + ":" + request.getServerPort()
//                    + request.getRequestURI();
//            String method = request.getMethod();
//            String queryString = request.getQueryString();
//            // 参数
//            Object[] args = joinPoint.getArgs();
//            String params = null;
//            if (null != args && args.length > 0) {
//                if ("POST".equals(method)) {
//                    Object object = args[0];
//                    params = JSONObject.toJSONString(object);
//                } else if ("GET".equals(method)) {
//                    params = queryString;
//                }
//                logger.info(" **** PrintParams requestMethod:{} ,requestUrl:{},  param:{}", method,
//                        requestUrl, params);
//            } else {
//                logger.warn(" **** PrintParams   not  data ! ");
//            }
//        } catch (Exception e) {
//            logger.error("PrintParamsAspect  error:{}", e);
//        }

    }
}
