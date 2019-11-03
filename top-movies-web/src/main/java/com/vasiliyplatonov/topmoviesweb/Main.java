package com.vasiliyplatonov.topmoviesweb;

import com.vasiliyplatonov.topmoviesweb.controller.KinopoiskTopByDate;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.io.File;


@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private Environment env;

    @Autowired
    public Main(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) throws LifecycleException {
        LOG.info("start web app");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        Tomcat tomcat = context.getBean(Tomcat.class);
        Context tomcatCtx = context.getBean(Context.class);

        String servletName = "KinopoiskTopByDate";
        String urlPattern = "/*";
        tomcat.addServlet(tomcatCtx.getPath(), servletName, new KinopoiskTopByDate());
        tomcatCtx.addServletMappingDecoded(urlPattern, servletName);

        tomcat.start();
        tomcat.getServer().await();

        LOG.info("finish web app");
    }

    @Bean
    public Tomcat tomcat() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("top-movies-webapp-tmp");
        tomcat.setPort(Integer.parseInt(env.getProperty
                ("tomcat.serverPort", "8080")));

        return tomcat;
    }

    @Bean
    public Context tomcatContext() {
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath(); // /top-movies-web/src/main/resources/webapp

        return tomcat().addContext(contextPath, docBase);
    }

}
