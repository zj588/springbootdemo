package com.imooc.jay.config.dao;


import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
// 配置mybatis mapper的扫描路径
@MapperScan("com.imooc.jay.dao")
public class DataSourceConfiguration {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "springstudy.datasource")
    public DataSource createDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
