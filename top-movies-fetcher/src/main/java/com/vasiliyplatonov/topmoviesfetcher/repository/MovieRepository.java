package com.vasiliyplatonov.topmoviesfetcher.repository;

import com.vasiliyplatonov.topmoviesfetcher.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {
    void insert(Movie m);

    void insertAll(List<Movie> movies);

    Movie getById(long id);

    List<Movie> getAllByDate(LocalDate date);

    List<Movie> getAll();
}
