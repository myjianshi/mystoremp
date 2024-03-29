package edu.gyc.histore.config;


import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局时间格式化  去掉 "T",只能解决json格式的时间
 *https://www.cnblogs.com/carrychan/p/9883172.html
 * @author kangjia
 * @date 2019/10/31 10:32
 */
@Configuration
@Slf4j
public class LocalDateTimeConfig {

    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
       // log.info("Execute LocalDateTimeConfig∂");
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }
}
