package com.dyc.reggie_take_out1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@ServletComponentScan
@EnableTransactionManagement
public class ReggieTakeOut1Application {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeOut1Application.class, args);
        log.info("项目启动成功");
    }

}
