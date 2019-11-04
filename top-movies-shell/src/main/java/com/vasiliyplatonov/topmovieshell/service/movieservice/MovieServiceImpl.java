package com.vasiliyplatonov.topmovieshell.service.movieservice;


import com.vasiliyplatonov.topmovieshell.entity.Movie;
import com.vasiliyplatonov.topmovieshell.repository.MovieRepository;
import com.vasiliyplatonov.topmovieshell.service.fetcher.MovieTopFetcher;
import com.vasiliyplatonov.topmovieshell.service.topsource.GettingTopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service("movieService")
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repo;
    private final MovieTopFetcher fetcher;

    @Autowired
    @Lazy
    public MovieServiceImpl(MovieRepository repo, MovieTopFetcher fetcher) {
        this.repo = repo;
        this.fetcher = fetcher;
    }


    @Override
    public List<Movie> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Movie> getAllByDate(LocalDate date) {
        return repo.getAllByDate(date);
    }

    @Override
    public List<Movie> getFirstNByDate(LocalDate date, int n) throws GettingTopException {
        return repo.getFirstNByDate(date, n);
    }

    @Override
    public void save(Movie m) {
        repo.insert(m);
    }

    @Override
    public void saveAll(List<Movie> movies) {
        repo.insertAll(movies);
    }

    @Override
    public List<Movie> fetchTopByDate(LocalDate date) throws GettingTopException {
        return fetcher.fetchTopByDate(date);
    }
}
