package com.vasiliyplatonov.topmoviesfatcher.service;

import com.vasiliyplatonov.topmoviesfatcher.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> fetchTop();

    void save(List<Movie> movies);
}
