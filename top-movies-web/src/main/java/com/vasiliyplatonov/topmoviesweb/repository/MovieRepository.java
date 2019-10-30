package com.vasiliyplatonov.topmoviesweb.repository;

import com.vasiliyplatonov.topmoviesweb.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {

    /**
     * Return all
     *
     * @param date - specific date of rating
     * */
    List<Movie> getAllByDate(LocalDate date);

    /**
     * Return specific count of entity
     *
     * @param date - specific date of rating
     * @param n - count of movies from rating with specific date
     * */
    List<Movie> getNByDate(LocalDate date, int n);

    List<Movie> getAll();
}
