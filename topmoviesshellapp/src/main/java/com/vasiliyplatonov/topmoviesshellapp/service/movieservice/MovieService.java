package com.vasiliyplatonov.topmoviesshellapp.service.movieservice;


import com.vasiliyplatonov.topmoviesshellapp.entity.Movie;
import com.vasiliyplatonov.topmoviesshellapp.service.topsource.GettingTopException;


import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    /**
     * Returns all movies with a specific date
     *
     * @param date - specific date of rating
     */
    List<Movie> getAllByDate(LocalDate date);

    /**
     * Returns the first 'n' entities with a specific date
     *
     * @param date - specific date of rating
     * @param n    - number of the first movies from rating
     */
    List<Movie> getFirstNByDate(LocalDate date, int n) throws GettingTopException;

    void save(Movie m);

    void saveAll(List<Movie> movies);

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

}
