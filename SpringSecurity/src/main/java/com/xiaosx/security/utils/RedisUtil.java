package com.xiaosx.security.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author x1aosx
 * @ClassName RedisUtil.java
 * @Description TODO
 * @createTime 2022年07月26日 18:51:00
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /*
     * @Author x1aosx
     * @Description 通过键获取内容
     * @Date 18:53 2022/7/26
     * @param key:  键
     * @return java.lang.String
     **/
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /*
     * @Author x1aosx
     * @Description 写入
     * @Date 18:54 2022/7/26
     * @param key:
     * @param value:
     * @return boolean
     **/
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @Author x1aosx
     * @Description 写入键值对并设置时间，单位为秒
     * @Date 10:27 2022/7/27
     * @param key:
     * @param value:
     * @param time:
     * @return boolean
     **/
    public boolean set(final String key,Object value,long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else {
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /*
     * @Author x1aosx
     * @Description 更新
     * @Date 18:54 2022/7/26
     * @param key:
     * @param value:
     * @return boolean
     **/
    public boolean getAndSet(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /*
     * @Author x1aosx
     * @Description 删除
     * @Date 18:55 2022/7/26
     * @param key: 键
     * @return boolean
     **/
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
