package service;

import domian.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    List<Movie> fetchTop() throws IOException;
}
