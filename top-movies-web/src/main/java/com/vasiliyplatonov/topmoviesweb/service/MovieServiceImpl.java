package com.vasiliyplatonov.topmoviesweb.service;

import com.vasiliyplatonov.topmoviesfetcher.service.topsource.GettingTopException;
import com.vasiliyplatonov.topmoviesweb.entity.Movie;
import com.vasiliyplatonov.topmoviesweb.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository repo;


    @Autowired
    public MovieServiceImpl(MovieRepository repo) {
        this.repo = repo;
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
}
