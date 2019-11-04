package com.vasiliyplatonov.topmoviesshellapp.service.fetcher;



import com.vasiliyplatonov.topmoviesshellapp.TestContextConfiguration;
import com.vasiliyplatonov.topmoviesshellapp.entity.Movie;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.GettingTopException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MovieTopFetcherTest {
    private MovieTopFetcher topFetcher;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestContextConfiguration.class);
        topFetcher = context.getBean(MovieTopFetcher.class);
        AssertionsForClassTypes.assertThat(topFetcher).isNotNull();
    }

    @Test
    public void fetchCurrentTop() throws GettingTopException {
        List<Movie> movies = topFetcher.fetchCurrentTop();

        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(250);

        // Check that all properties was filled in the each movie
        checkTop(movies);

        // Check date
        movies.forEach(m -> assertThat(m
                .getTopDate())
                .isEqualTo(LocalDate.now()));

    }

    @Test
    public void fetchTopByDate() throws GettingTopException {
        LocalDate date = LocalDate.of(2019, 11, 1);

        List<Movie> movies = topFetcher.fetchTopByDate(date);

        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(250);

        // Check that all properties was filled in the each movie
        checkTop(movies);

        // Check date
        movies.forEach(m -> assertThat(m.getTopDate()).isEqualTo(date));

    }

    private void checkTop(List<Movie> movies) {
        movies.forEach(m -> {
            assertThat(m).isNotNull();
            assertThat(m.getTitle()).isNotBlank();
            assertThat(Integer.parseInt(m.getYear()))
                    .isNotNegative()
                    .isNotZero()
                    .isNotNull()
                    .isLessThan(Year.now().getValue() + 30); // year should be less than now +30 years
            assertThat(m.getRating()).isBetween(0f, 10f);
            assertThat(m.getVotes())
                    .isNotNegative()
                    .isNotZero();
            assertThat(m.getPosition()).isBetween(1, 250);
            assertThat(m.getTopDate()).isNotNull();
        });
    }
}