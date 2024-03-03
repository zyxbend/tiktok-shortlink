package com.pddbend.shortlink.gateway.config;

import lombok.Data;

import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2024-03-03-19:15
 * @Description: 过滤器配置
 */
@Data
public class Config {

    /**
     * 白名单前置路径
     */
    private List<String> whitePathList;
}
