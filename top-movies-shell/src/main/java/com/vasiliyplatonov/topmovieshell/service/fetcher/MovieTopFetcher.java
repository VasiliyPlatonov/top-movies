package com.vasiliyplatonov.topmovieshell.service.fetcher;


import com.vasiliyplatonov.topmovieshell.entity.Movie;
import com.vasiliyplatonov.topmovieshell.service.topsource.GettingTopException;

import java.time.LocalDate;
import java.util.List;

public interface MovieTopFetcher {

    /**
     * Returns the top movies for today`s date
     */
    default List<Movie> fetchCurrentTop() throws GettingTopException {
        return fetchTopByDate(LocalDate.now());
    }

    /**
     * Returns the top movies for a specific date
     *
     * @param date - specific date of the top
     */
    List<Movie> fetchTopByDate(LocalDate date) throws GettingTopException;

    void save(List<Movie> movies);
}
