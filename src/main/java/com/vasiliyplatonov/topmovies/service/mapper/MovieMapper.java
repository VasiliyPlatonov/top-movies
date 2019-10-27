package com.vasiliyplatonov.topmovies.service.mapper;

import com.vasiliyplatonov.topmovies.domain.Movie;

public interface MovieMapper<T> {
    Movie map(T from);
}
