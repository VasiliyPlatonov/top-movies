package com.vasiliyplatonov.topmoviesshellapp;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"com.vasiliyplatonov.topmoviesshellapp"})
@PropertySource("classpath:app.properties")
public class TestContextConfiguration {

    private Environment env;

    @Autowired
    public TestContextConfiguration(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("url.test"));
        dataSource.setUsername(env.getRequiredProperty("username"));
        dataSource.setPassword(env.getRequiredProperty("password"));

        return dataSource;
    }

}
