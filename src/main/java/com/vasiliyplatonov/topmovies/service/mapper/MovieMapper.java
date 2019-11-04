package com.vasiliyplatonov.topmovies.service.mapper;


import com.vasiliyplatonov.topmovies.entity.Movie;

import java.time.LocalDate;

public interface MovieMapper<T> {
    Movie map(T from, LocalDate topDate);
}
