package com.vasiliyplatonov.topmovies.service.mapper;


import com.vasiliyplatonov.topmovies.entity.Movie;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KinopoiskTopMapper implements MovieMapper<Element> {

    @Override
    public Movie map(Element row, LocalDate topDate) {
        String title = rowToTitle(row);
        String year = rowToYear(row);
        float rating = rowToRating(row);
        int votes = rowToVotes(row);
        int position = rowToPosition(row);

        return new Movie(title, year, rating, votes, position, topDate);
    }

    private static int rowToPosition(Element row) {
        return Integer.parseInt(row.children().get(0)
                .text()
                .replaceAll("[^0123456789]", ""));
    }

    private static int rowToVotes(Element row) {
        return Integer.parseInt((row.children().get(2)
                .select("span")
                .text()
                .replaceAll("[^0123456789]", "")));
    }

    private static float rowToRating(Element row) {
        return Float.parseFloat(row.children().get(2)
                .select("a[href*=votes]")
                .text());
    }

    private static String rowToTitle(Element row) {
        String yearPattern = "(\\s\\(\\d{4}\\))";

        String title = row.children().get(1).select("span.text-grey").text();
        if (title == null || title.isEmpty()) { // if it is russian movie
            title = row.children().get(1).select("a")
                    .text()
                    .replaceFirst(yearPattern, "");
        }
        return title;
    }

    private static String rowToYear(Element row) {
        String rusTitle = row.children().get(1).select("a").text();
        return rusTitle.substring(rusTitle.lastIndexOf("(") + 1, rusTitle.lastIndexOf(")"));
    }
}
