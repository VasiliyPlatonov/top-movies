package com.vasiliyplatonov.topmoviesfatcher;

import com.vasiliyplatonov.topmoviesfatcher.domain.Movie;
import com.vasiliyplatonov.topmoviesfatcher.service.MovieService;
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
