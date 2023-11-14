package com.pddbend.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: pddbend
 * @Date: 2023-11-13-下午2:50
 * @Description: 后管系统主启动类
 */

@SpringBootApplication
@MapperScan("com.pddbend.shortlink.admin.dao.mapper")
public class ShortlinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortlinkAdminApplication.class,args);
    }
}
