package com.vasiliyplatonov.topmoviesfetcher;

import com.vasiliyplatonov.topmoviesfetcher.entity.Movie;
import com.vasiliyplatonov.topmoviesfetcher.service.MovieTopFetcher;
import com.vasiliyplatonov.topmoviesfetcher.service.topsource.GettingTopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@ComponentScan
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("The top-movies-fetcher app is started");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        MovieTopFetcher fetcherService = context.getBean(MovieTopFetcher.class);
        try {
            List<Movie> currentTop = fetcherService.fetchCurrentTop();
            fetcherService.save(currentTop);
        } catch (GettingTopException e) {
            LOG.error(e.getMessage(), e);
        }
        context.close();

        LOG.info("The top-movies-fetcher app is finished");
    }

}
