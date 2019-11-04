package com.vasiliyplatonov.topmoviesshellapp.service.topsource;

import java.time.LocalDate;

public interface TopSource<T> {
    default T getCurrentTop() throws GettingTopException {
        return getTopByDate(LocalDate.now());
    }

    T getTopByDate(LocalDate date) throws GettingTopException;
}
