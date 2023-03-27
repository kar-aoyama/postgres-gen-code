package cn.wd.lzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wd.lzl.*.mapper")
public class GenApp {
    public static void main(String[] args) {
        SpringApplication.run(GenApp.class, args);
    }
}
