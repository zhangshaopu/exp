package com.example.exp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * @Description: TransactoionConfig配置
 * @Author: zsp
 * @Date: 2023/6/23 0:24
 */
@Configuration
public class TransactoionConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
        platformTransactionManager.setDataSource(dataSource);
        return platformTransactionManager;
    }

}
