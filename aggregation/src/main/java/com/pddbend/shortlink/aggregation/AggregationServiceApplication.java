package com.pddbend.shortlink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.pddbend.shortlink.admin",
        "com.pddbend.shortlink.project"
})
@MapperScan(value = {
        "com.pddbend.shortlink.project.dao.mapper",
        "com.pddbend.shortlink.admin.dao.mapper"
})
public class AggregationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregationServiceApplication.class, args);
    }
}