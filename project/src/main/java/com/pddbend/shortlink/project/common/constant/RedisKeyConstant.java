package com.pddbend.shortlink.project.common.constant;

/**
 * @Author: pddbend
 * @Date: 2023-11-28-14:56
 * @Description: RedisKey常量
 */
public class RedisKeyConstant {
    /**
     * 短链接跳转前缀 Key
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * 短链接空值跳转前缀 Key
     */
    public static final String GOTO_IS_NULL_SHORT_LINK_KEY = "short-link_is-null_goto_%s";


    /**
     * 短链接跳转分布式锁前缀 Key
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";

}
