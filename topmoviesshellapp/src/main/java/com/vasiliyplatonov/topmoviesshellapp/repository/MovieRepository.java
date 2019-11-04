package com.vasiliyplatonov.topmoviesshellapp.repository;

import com.vasiliyplatonov.topmoviesshellapp.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {

    void insert(Movie m);

    void insertAll(List<Movie> movies);

    /**
     * Returns all movies with a specific date
     *
     * @param date - specific date of rating
     */
    List<Movie> getAllByDate(LocalDate date);

    /**
     * Returns the first 'n' entities with a specific date
     *
     * @param date - specific date of rating
     * @param n    - number of the first movies from rating
     */
    List<Movie> getFirstNByDate(LocalDate date, int n);

    List<Movie> getAll();
}
