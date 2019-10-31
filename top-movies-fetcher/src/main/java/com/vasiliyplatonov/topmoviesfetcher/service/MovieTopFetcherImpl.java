package com.vasiliyplatonov.topmoviesfetcher.service;

import com.vasiliyplatonov.topmoviesfetcher.entity.Movie;
import com.vasiliyplatonov.topmoviesfetcher.repository.MovieRepository;
import com.vasiliyplatonov.topmoviesfetcher.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmoviesfetcher.service.topsource.GettingTopException;
import com.vasiliyplatonov.topmoviesfetcher.service.topsource.TopSource;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("topFetcher")
public class MovieTopFetcherImpl implements MovieTopFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(MovieTopFetcherImpl.class);

    private final TopSource<Elements> topSource;
    private final MovieMapper<Element> mapper;
    private final MovieRepository repo;

    @Autowired
    public MovieTopFetcherImpl(TopSource<Elements> topSource, MovieMapper<Element> mapper, MovieRepository repository) {
        this.topSource = topSource;
        this.mapper = mapper;
        this.repo = repository;
    }

    @Override
    public List<Movie> fetchTopByDate(LocalDate topDate) throws GettingTopException {
        Elements ratingRows = topSource.getCurrentTop();
        LOG.info("The top of movies was fetched");
        return ratingRows.stream()
                .map(row -> mapper.map(row, topDate))
                .collect(Collectors.toList());
    }

    @Override
    public void save(List<Movie> movies) {
        repo.insertAll(movies);
        LOG.info("The top of movies was saved");
    }
}
