package service.mapper;

import domian.Movie;

public interface MovieMapper<T> {
    Movie map(T from);
}
