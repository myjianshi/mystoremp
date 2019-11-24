package edu.gyc.histore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("edu.gyc.histore.dao")
@SpringBootApplication
public class HistoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HistoreApplication.class, args);
    }

}
