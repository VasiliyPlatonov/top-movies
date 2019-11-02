package com.vasiliyplatonov.topmoviesweb.repository;

import com.vasiliyplatonov.topmoviesweb.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {

    /**
     * Returns all movies with a specific date
     *
     * @param date - specific date of rating
     * */
    List<Movie> getAllByDate(LocalDate date);

    /**
     * Returns the first 'n' entities with a specific date
     *
     * @param date - specific date of rating
     * @param n - number of the first movies from rating
     * */
    List<Movie> getFirstNByDate(LocalDate date, int n);

    List<Movie> getAll();
}
