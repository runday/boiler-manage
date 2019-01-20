package com.itdreamworks.boilermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.itdreamworks.boilermanage.mapper")
public class BoilerManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoilerManageApplication.class, args);
    }
}