package com.vasiliyplatonov.topmovies.service;

import com.vasiliyplatonov.topmovies.domain.Movie;
import com.vasiliyplatonov.topmovies.service.mapper.KinopoiskTopMapper;
import org.junit.Test;
import com.vasiliyplatonov.topmovies.service.topsource.KinopoiskTop;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class MovieServiceImplTest {
    private MovieService service = new MovieServiceImpl(new KinopoiskTop(), new KinopoiskTopMapper());

    @Test
    public void fetchKinopoiskTop() throws IOException {
        List<Movie> movies = service.fetchTop();

        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(250);

        // Check that all properties was filled in the each movie
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
        });


    }
}