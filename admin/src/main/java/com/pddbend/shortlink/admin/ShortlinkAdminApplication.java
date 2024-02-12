package com.pddbend.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: pddbend
 * @Date: 2023-11-13-下午2:50
 * @Description: 后管系统主启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.pddbend.shortlink.admin.remote")
@MapperScan("com.pddbend.shortlink.admin.dao.mapper")
public class ShortlinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortlinkAdminApplication.class,args);
    }
}
