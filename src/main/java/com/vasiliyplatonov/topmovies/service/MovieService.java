package com.vasiliyplatonov.topmovies.service;

import com.vasiliyplatonov.topmovies.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> fetchTop();

    void save(List<Movie> movies);
}
