package com.vasiliyplatonov.topmovies;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;

import java.io.IOException;

@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Shell shell = context.getBean(Shell.class);
        shell.run(context.getBean(InputProvider.class));
    }
}


// TODO: Make service class that will manage tomcat: add servlets, context