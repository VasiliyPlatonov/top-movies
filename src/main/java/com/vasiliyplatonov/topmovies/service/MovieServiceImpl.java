package com.vasiliyplatonov.topmovies.service;

import com.vasiliyplatonov.topmovies.domain.Movie;
import com.vasiliyplatonov.topmovies.repository.MovieRepository;
import com.vasiliyplatonov.topmovies.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmovies.service.topsource.TopSource;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final TopSource<Elements> topSource;
    private final MovieMapper<Element> mapper;
    private final MovieRepository repo;

    @Autowired
    public MovieServiceImpl(TopSource<Elements> topSource, MovieMapper<Element> mapper, MovieRepository repository) {
        this.topSource = topSource;
        this.mapper = mapper;
        this.repo = repository;
    }

    @Override
    public List<Movie> fetchTop() {
        Elements ratingRows = null;
        try {
            ratingRows = topSource.getTop();
        } catch (IOException e) {
            LOG.error("The error occurs while trying to fetch the rating of the films");
            e.printStackTrace();
        }
        LOG.info("The rating of the films was fetched");
        return ratingRows.stream()
                .map(mapper::map)
                .collect(Collectors.toList());

    }

    @Override
    public void save(List<Movie> movies) {
        repo.insertAll(movies);
    }
}
