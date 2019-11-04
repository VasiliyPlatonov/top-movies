package com.vasiliyplatonov.topmovieshell;

import com.vasiliyplatonov.topmovieshell.command.H2TcpServerCommands;
import com.vasiliyplatonov.topmovieshell.config.AppConfig;
import com.vasiliyplatonov.topmovieshell.config.HibernateConfig;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:app.properties")
@Import({HibernateConfig.class, AppConfig.class})
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
