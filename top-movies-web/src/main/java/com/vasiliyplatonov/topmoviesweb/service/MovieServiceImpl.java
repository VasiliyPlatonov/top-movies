package com.vasiliyplatonov.topmoviesweb.service;

import com.vasiliyplatonov.topmoviesweb.domain.Movie;
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
    public MovieServiceImpl(MovieRepository repository) {
        this.repo = repository;
    }

    @Override
    public List<Movie> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Movie> getAllByDate(LocalDate date) {
        return repo.getAllByDate(date);
    }
}
