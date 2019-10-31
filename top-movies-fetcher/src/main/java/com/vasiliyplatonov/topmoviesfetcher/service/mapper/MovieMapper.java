package com.vasiliyplatonov.topmoviesfetcher.service.mapper;

import com.vasiliyplatonov.topmoviesfetcher.entity.Movie;

import java.time.LocalDate;

public interface MovieMapper<T> {
    Movie map(T from, LocalDate topDate);
}
