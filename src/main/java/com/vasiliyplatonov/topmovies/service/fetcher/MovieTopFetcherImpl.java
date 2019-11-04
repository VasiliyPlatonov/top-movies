package com.vasiliyplatonov.topmovies.service.fetcher;


import com.vasiliyplatonov.topmovies.entity.Movie;
import com.vasiliyplatonov.topmovies.repository.MovieRepository;
import com.vasiliyplatonov.topmovies.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmovies.service.topsource.GettingTopException;
import com.vasiliyplatonov.topmovies.service.topsource.TopSource;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public MovieTopFetcherImpl(TopSource<Elements> topSource, MovieMapper<Element> mapper, @Lazy MovieRepository repository) {
        this.topSource = topSource;
        this.mapper = mapper;
        this.repo = repository;
    }

    @Override
    public List<Movie> fetchTopByDate(LocalDate topDate) throws GettingTopException {
        Elements topRows = topSource.getTopByDate(topDate);
        LOG.info("The top of movies was fetched");
        return topRows.stream()
                .map(row -> mapper.map(row, topDate))
                .collect(Collectors.toList());
    }

    @Override
    public void save(List<Movie> movies) {
        repo.insertAll(movies);
        LOG.info("The top of movies was saved");
    }
}
