package com.xmy.common.redis;

import com.alibaba.fastjson.JSON;
import com.xmy.common.service.JedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 　　* @Description: 通用redis缓存,使用反射,动态代理技术 (面向切片编程)
 * 　　* @author xmy
 * 　　* @date 2019-07-09 16:44
 *
 */
@Component
@Aspect
public class RedisCacheAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisService jedisService;

    @Pointcut("execution(public * com.xmy.service..*.*(..))")
    public void webAspect() {
    }

    @Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //得到类名、方法名和参数
        String redisResult = "";
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        logger.info("args: " + JSON.toJSON(args).toString());
        //根据雷鸣,方法名和参数生成key
        String key = getKey(className, methodName, args);
        logger.info("生成的key[{}]", key);

        //得到被代理的方法
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisCache.class).type();
        int cacheTime = method.getAnnotation(RedisCache.class).cacheTime();
        Object result = null;
        if (!jedisService.exists(key)) {
            logger.info("缓存: 未命中");
            //缓存不存在，则调用原方法，并将结果放入缓存中
            result = pjp.proceed(args);
            redisResult = JSON.toJSONString(result);
            jedisService.set(key, redisResult, cacheTime);
        } else {
            // 缓存命中
            logger.info("缓存: 命中");
            redisResult = jedisService.get(key);
            // 得到被代理方法的返回值类型
            Class<?> returnType = method.getReturnType();
            logger.info("returnType: " + returnType);
            result = JSON.parseObject(redisResult, returnType);
        }
        return result;
    }

    /**
     * @Description: 生成key
     * @Param:
     * @return:
     * @Author:
     * @Date: 2018/5/16
     */
    private String getKey(String className, String methodName, Object[] args) {
        StringBuffer sb = new StringBuffer("SpringBoot2:");
        sb.append(className);
        sb.append("_");
        sb.append(methodName);
        sb.append("_");
        for (Object obj : args) {
//            logger.info("obj:" + obj);
            if (obj != null) {
                sb.append(obj + "");
                sb.append("_");
            }
        }
        return sb.toString();
    }
}
