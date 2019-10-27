package com.vasiliyplatonov.topmovies.service;

import com.vasiliyplatonov.topmovies.domain.Movie;
import com.vasiliyplatonov.topmovies.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmovies.service.topsource.TopSource;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private final TopSource<Elements> topSource;
    private final MovieMapper<Element> mapper;

    @Autowired
    public MovieServiceImpl(TopSource<Elements> topSource, MovieMapper<Element> mapper) {
        this.topSource = topSource;
        this.mapper = mapper;
    }

    @Override
    public List<Movie> fetchTop() throws IOException {
        Elements ratingRows = topSource.getTop();
        return ratingRows.stream()
                .map(mapper::map)
                .collect(Collectors.toList());

    }
}
