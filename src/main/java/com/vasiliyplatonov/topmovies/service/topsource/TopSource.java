package com.vasiliyplatonov.topmovies.service.topsource;

import java.io.IOException;

public interface TopSource<T> {
    T getTop() throws IOException;
}
