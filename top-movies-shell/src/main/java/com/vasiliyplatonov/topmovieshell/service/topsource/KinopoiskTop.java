package com.vasiliyplatonov.topmovieshell.service.topsource;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class KinopoiskTop implements TopSource<Elements> {
    private static final String URL = "https://www.kinopoisk.ru/top/day/";
    private static final String cssQrySelector = "tr[id~=top250_place_?]";
    private static final Logger LOG = LoggerFactory.getLogger(KinopoiskTop.class);

    @Override
    public Elements getTopByDate(LocalDate date) throws GettingTopException {
        String URL = KinopoiskTop.URL + date;
        LOG.info("Starts getting top movies from the url: " + URL);

        try {
            return Jsoup.connect(URL)
                    .get()
                    .select(cssQrySelector);
        } catch (IOException e) {
            throw new GettingTopException(
                    "The error occurs while trying to executing GET request to URL: " + URL, e);
        }
    }
}
