package com.vasiliyplatonov.topmoviesfetcher;

import com.vasiliyplatonov.topmoviesshellapp.config.AppConfig;
import com.vasiliyplatonov.topmoviesshellapp.config.HibernateConfig;
import com.vasiliyplatonov.topmoviesshellapp.entity.Movie;
import com.vasiliyplatonov.topmoviesshellapp.repository.MovieRepository;
import com.vasiliyplatonov.topmoviesshellapp.repository.MovieRepositoryJpa;
import com.vasiliyplatonov.topmoviesshellapp.service.fetcher.MovieTopFetcher;
import com.vasiliyplatonov.topmoviesshellapp.service.fetcher.MovieTopFetcherImpl;
import com.vasiliyplatonov.topmoviesshellapp.service.mapper.KinopoiskTopMapper;
import com.vasiliyplatonov.topmoviesshellapp.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.GettingTopException;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.KinopoiskTop;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.TopSource;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Import({AppConfig.class,
        HibernateConfig.class})
public class KinopoiskTopFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(KinopoiskTopFetcher.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(KinopoiskTopFetcher.class);
        MovieTopFetcher fetcher = context.getBean(MovieTopFetcher.class);

        try {
            List<Movie> top = fetcher.fetchCurrentTop();
            LOG.info("The top of movies from site Kinopoisk.ru by " + LocalDate.now() + " was fetched.");
            fetcher.save(top);
            LOG.info("The top of movies from site Kinopoisk.ru by " + LocalDate.now() + " was saved in database.");
        } catch (GettingTopException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Bean
    MovieTopFetcher movieTopFetcher() {
        return new MovieTopFetcherImpl(topSource(), mapper(), repository());
    }

    @Bean
    TopSource<Elements> topSource() {
        return new KinopoiskTop();
    }

    @Bean
    MovieMapper<Element> mapper() {
        return new KinopoiskTopMapper();
    }


    @Bean
    MovieRepository repository() {
        return new MovieRepositoryJpa();
    }
}
