package com.imooc.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayApplication.class, args);


//        // 根据不同的环境加载不同的配置文件
//        SpringApplication application = new SpringApplication(JayApplication.class);
//
//        ConfigurableEnvironment environment = new StandardEnvironment();
//        // 设置环境，可以根据不同的环境变量，读取不同的配置文件
//        // 例如环境为dev，加载的配置文件为application.properties和application-dev.properties，如果有重复的属性，以application-dev.properties的属性为主
//        environment.setActiveProfiles("dev");
//        application.setEnvironment(environment);
//
//
//        application.run(args);
    }
}
