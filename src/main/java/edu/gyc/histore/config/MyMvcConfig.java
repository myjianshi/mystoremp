package edu.gyc.histore.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by wangbiao-019 on 2018/3/21.
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*资源处理器*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/"+"/img/");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/"+"/static/");
    }

}
