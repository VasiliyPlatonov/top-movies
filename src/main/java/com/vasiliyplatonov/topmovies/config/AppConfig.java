package com.vasiliyplatonov.topmovies.config;


import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.File;


@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    private Environment env;

    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public Tomcat tomcat() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("top-movies-webapp-tmp");
        tomcat.setPort(Integer.parseInt(env.getProperty
                ("tomcat.serverPort", "8080")));

        return tomcat;
    }

    @Bean Context tomcatCtx(){
        final String contextPath = "";
        final String docBase = new File(".").getAbsolutePath();
        return tomcat().addContext(contextPath, docBase);
    }

}
