package com.vasiliyplatonov.topmoviesfatcher.service;

import com.vasiliyplatonov.topmoviesfatcher.Main;
import com.vasiliyplatonov.topmoviesfatcher.domain.Movie;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MovieServiceImplTest {
    private MovieService service;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        service = context.getBean(MovieService.class);
        AssertionsForClassTypes.assertThat(service).isNotNull();
    }

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