package com.pddbend.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: pddbend
 * @Date: 2023-11-13-下午2:50
 * @Description: 后管系统主启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.pddbend.shortlink.project.dao.mapper")
public class ShortlinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortlinkApplication.class,args);
    }
}
