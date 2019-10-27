package service;

import domian.Movie;
import service.mapper.MovieMapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.topsource.TopSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    private TopSource<Elements> topSource;
    private MovieMapper<Element> mapper;

    public MovieServiceImpl(TopSource<Elements> topSource, MovieMapper<Element> mapper) {
        this.topSource = topSource;
        this.mapper = mapper;
    }

    @Override
    public List<Movie> fetchTop() throws IOException {
        Elements ratingRows = topSource.getTop();
        return ratingRows.stream()
                .map(row -> mapper.map(row))
                .collect(Collectors.toList());

    }
}
