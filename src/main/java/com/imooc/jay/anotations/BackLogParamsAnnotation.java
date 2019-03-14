package com.imooc.jay.anotations;

import java.lang.annotation.*;

/**
 * 请求参数信息打印注解类
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BackLogParamsAnnotation {
}
