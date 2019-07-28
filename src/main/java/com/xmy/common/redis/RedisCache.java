package com.xmy.common.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis代理AOP接口(自定义)
 */
// 触发类型: Runtime 时触发
@Retention(RetentionPolicy.RUNTIME)
// 注解使用范围:方法,类或接口
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {
    /**
     * 数据返回类型
     *
     * @return
     */
    Class type();

    /**
     * 数据缓冲时间单位:秒(s)
     * 默认10分钟
     * 0 : 永不过期
     *
     * @return
     */
    int cacheTime() default 600;
}
