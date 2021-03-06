package com.vasiliyplatonov.topmoviesshellapp.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Lazy
@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class HibernateConfig {

    private Environment env;

    @Lazy
    @Autowired
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Lazy
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.vasiliyplatonov.topmoviesshellapp.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Lazy
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("url.tcp.server"));
        dataSource.setUsername(env.getRequiredProperty("username"));
        dataSource.setPassword(env.getRequiredProperty("password"));

        return dataSource;
    }

    @Lazy
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }


    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        hibernateProp.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        hibernateProp.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        hibernateProp.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
        hibernateProp.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        hibernateProp.put("hibernate.max_fetch_depth", env.getRequiredProperty("hibernate.max_fetch_depth"));
        hibernateProp.put("hibernate.jdbc.batch_size", env.getRequiredProperty("hibernate.jdbc.batch_size"));
        hibernateProp.put("hibernate.jdbc.fetch_size", env.getRequiredProperty("hibernate.max_fetch_depth"));

        return hibernateProp;
    }
}