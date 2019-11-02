package com.vasiliyplatonov.topmoviesweb.repository;

import com.vasiliyplatonov.topmoviesweb.TestContextConfiguration;
import com.vasiliyplatonov.topmoviesweb.entity.Movie;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * For these tests had been prepared test database (./src/test/resources/top_movies_test)
 * which stores 500 of movies, among that movies:
 * - 250 movies by date "2019-11-01"
 * - 250 movies by date "2019-11-02"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieRepositoryJpaTest {

    private MovieRepository repository;


// boolean isDbExists = Files.exists(Paths.get("./src/test/resources/top_movies_test"));
//        if (!isDbExists) {
//            LOG.warn("The test database on the path: './src/test/resources/top_movies_test' does not exists");
//        }

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestContextConfiguration.class);
        repository = context.getBean(MovieRepository.class);
        assertThat(repository).isNotNull();

        // checking that the database exists
        assertThat(isDbExist(
                context.getEnvironment().getRequiredProperty("url.test")))
                .isTrue();
    }


    @Test
    public void getFirstNByDate() {
        int maxTopPosition = 10;
        LocalDate goodDate = LocalDate.of(2019, 11, 1);
        LocalDate badDate = LocalDate.of(2018, 10, 30);

        // Positive test with goodDate
        List<Movie> movies = repository.getFirstNByDate(goodDate, maxTopPosition);

        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(maxTopPosition);

        movies.forEach(m -> {
            assertThat(m).isNotNull();
            assertThat(m.getPosition())
                    .isLessThanOrEqualTo(maxTopPosition)
                    .isGreaterThan(0);
        });


        // Negative test with badDate
        movies = repository.getFirstNByDate(badDate, maxTopPosition);
        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isEmpty();
    }


    @Test
    public void getAll() {
        List<Movie> movies = repository.getAll();

        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(500);

//        // Check that all properties was filled in the each movie
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

    @Test
    public void getAllByDate() {
        LocalDate goodDate = LocalDate.of(2019, 11, 1);
        LocalDate badDate = LocalDate.of(2018, 10, 30);

        List<Movie> movGDate = repository.getAllByDate(goodDate);
        List<Movie> movBDate = repository.getAllByDate(badDate);

        // Check goodDate
        assertThat(movGDate)
                .isNotEmpty()
                .hasSize(250);

        movGDate.forEach(m -> {
            assertThat(m.getTopDate()).isEqualTo(goodDate);
        });

        // Check badDate
        assertThat(movBDate)
                .isNotNull()
                .isEmpty();

        movBDate.forEach(m -> {
            assertThat(m.getTopDate()).isEqualTo(badDate);
        });
    }

    private boolean isDbExist(String testDbPath) {
        return Files.exists(Paths.get(
                testDbPath.substring(
                        testDbPath.lastIndexOf(":") + 1)
                        + ".mv.db"));
    }
}