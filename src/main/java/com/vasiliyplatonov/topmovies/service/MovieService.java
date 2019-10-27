package com.vasiliyplatonov.topmovies.service;

import com.vasiliyplatonov.topmovies.domain.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    List<Movie> fetchTop() throws IOException;
}
