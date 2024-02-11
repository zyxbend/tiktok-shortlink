package com.pddbend.shortlink.admin.common.constant;

/**
 * @Author: pddbend
 * @Date: 2023-11-17-下午4:20
 * @Description: 短链接后管 Redis，缓存常量类
 */
public class RedisCacheConstant {
    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
