package org.fall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("org.fall.mapper")
@SpringBootApplication
public class SqlMainApp {

    public static void main(String[] args) {
        SpringApplication.run(SqlMainApp.class, args);
    }
}
