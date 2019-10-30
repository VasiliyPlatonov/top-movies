package com.vasiliyplatonov.topmoviesweb.service;

import com.vasiliyplatonov.topmoviesweb.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getAllByDate(LocalDate date);

    List<Movie> getTenByDate(LocalDate date);
}
