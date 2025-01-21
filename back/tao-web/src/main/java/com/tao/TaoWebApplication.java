package com.tao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tao")
@MapperScan(basePackages = "com.tao.mapper")
public class TaoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaoWebApplication.class, args);
    }

}
