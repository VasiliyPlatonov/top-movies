package service.topsource;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class KinopoiskTop implements TopSource<Elements>  {
    private static final String URL = "https://www.kinopoisk.ru/top/";
    private static final String cssQrySelector = "tr[id~=top250_place_?]";

    @Override
    public Elements getTop() throws IOException {
        return Jsoup.connect(URL).get()
                .select(cssQrySelector);
    }
}
