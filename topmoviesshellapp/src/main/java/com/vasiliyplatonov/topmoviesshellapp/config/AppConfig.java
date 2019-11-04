package com.vasiliyplatonov.topmoviesshellapp.config;


import com.vasiliyplatonov.topmoviesshellapp.repository.MovieRepository;
import com.vasiliyplatonov.topmoviesshellapp.repository.MovieRepositoryJpa;
import com.vasiliyplatonov.topmoviesshellapp.service.fetcher.MovieTopFetcher;
import com.vasiliyplatonov.topmoviesshellapp.service.fetcher.MovieTopFetcherImpl;
import com.vasiliyplatonov.topmoviesshellapp.service.mapper.KinopoiskTopMapper;
import com.vasiliyplatonov.topmoviesshellapp.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.KinopoiskTop;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.TopSource;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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


//    @Lazy
//    @Bean
//    MovieTopFetcher movieTopFetcher() {
//        return new MovieTopFetcherImpl(topSource(), mapper(), repository());
//    }
//
//    @Lazy
//    @Bean
//    TopSource<Elements> topSource() {
//        return new KinopoiskTop();
//    }
//
//    @Lazy
//    @Bean
//    MovieMapper<Element> mapper() {
//        return new KinopoiskTopMapper();
//    }
//
//    @Lazy
//    @Bean
//    MovieRepository repository() {
//        return new MovieRepositoryJpa();
//    }
}
