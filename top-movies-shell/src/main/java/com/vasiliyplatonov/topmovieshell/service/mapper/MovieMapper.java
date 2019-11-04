package com.vasiliyplatonov.topmovieshell.service.mapper;


import com.vasiliyplatonov.topmovieshell.entity.Movie;

import java.time.LocalDate;

public interface MovieMapper<T> {
    Movie map(T from, LocalDate topDate);
}
