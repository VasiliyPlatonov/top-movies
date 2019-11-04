package com.vasiliyplatonov.topmoviesshellapp.service.mapper;


import com.vasiliyplatonov.topmoviesshellapp.entity.Movie;

import java.time.LocalDate;

public interface MovieMapper<T> {
    Movie map(T from, LocalDate topDate);
}
