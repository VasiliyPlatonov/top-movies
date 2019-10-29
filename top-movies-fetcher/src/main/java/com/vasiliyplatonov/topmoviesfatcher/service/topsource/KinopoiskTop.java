package com.vasiliyplatonov.topmoviesfatcher.service.topsource;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KinopoiskTop implements TopSource<Elements> {
    private static final String URL = "https://www.kinopoisk.ru/top/";
    private static final String cssQrySelector = "tr[id~=top250_place_?]";
    private static final Logger LOG = LoggerFactory.getLogger(KinopoiskTop.class);

    @Override
    public Elements getTop() throws IOException {
        LOG.info("KinopoiskTop starts getting top movies from the url: " + URL);
        return Jsoup.connect(URL).get()
                .select(cssQrySelector);
    }
}
