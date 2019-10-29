package com.vasiliyplatonov.topmovies;

import com.vasiliyplatonov.topmovies.domain.Movie;
import com.vasiliyplatonov.topmovies.service.MovieService;
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
        LOG.info("start app");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        MovieService movieService = context.getBean(MovieService.class);
        List<Movie> topMovies = movieService.fetchTop();
        movieService.save(topMovies);
        context.close();

        LOG.info("finish app");
    }
}
