package com.vasiliyplatonov.topmoviesfatcher.service.mapper;

import com.vasiliyplatonov.topmoviesfatcher.domain.Movie;

public interface MovieMapper<T> {
    Movie map(T from);
}
