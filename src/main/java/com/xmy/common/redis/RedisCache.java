package com.xmy.common.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis代理AOP接口
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {
    /**
     * 数据返回类型
     * @return
     */
    Class type();

    /**
     * 数据缓冲时间单位:秒(s)
     * 默认10分钟
     * @return
     */
    int cacheTime() default 600;
}
