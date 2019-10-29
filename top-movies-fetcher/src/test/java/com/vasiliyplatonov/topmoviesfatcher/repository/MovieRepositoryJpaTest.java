package com.vasiliyplatonov.topmoviesfatcher.repository;

import com.vasiliyplatonov.topmoviesfatcher.Main;
import com.vasiliyplatonov.topmoviesfatcher.domain.Movie;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieRepositoryJpaTest {
    private MovieRepository repository;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        repository = context.getBean(MovieRepository.class);
        assertThat(repository).isNotNull();
    }

    @Test
    public void stage1_insertAll() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("test movie1", "2009", 8.3f, 1000, 12));
        movies.add(new Movie("test movie2", "1997", 4.3f, 10000, 30));
        movies.add(new Movie("test movie3", "2019", 7.3f, 710000, 30, LocalDate.of(2019, 4, 10)));

        repository.insertAll(movies);
    }

    @Test
    public void stage2_getAll() {
        List<Movie> movies = repository.getAll();

        // Check that movies not null and was filled
        assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

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
    public void stage3_getAllByDate() {
        LocalDate dateNow = LocalDate.now();
        LocalDate testDate = LocalDate.of(2019, 4, 10);

        List<Movie> movByNow = repository.getAllByDate(dateNow);
        List<Movie> movByTestDate = repository.getAllByDate(testDate);

        // Check now date
        assertThat(movByNow)
                .isNotEmpty()
                .hasSize(2);

        movByNow.forEach(m -> {
            assertThat(m.getReceiptDate()).isEqualTo(dateNow);
        });

        // Check 2019-4-10
        assertThat(movByTestDate)
                .isNotEmpty()
                .hasSize(1);

        movByTestDate.forEach(m -> {
            assertThat(m.getReceiptDate()).isEqualTo(testDate);
        });
    }
}

