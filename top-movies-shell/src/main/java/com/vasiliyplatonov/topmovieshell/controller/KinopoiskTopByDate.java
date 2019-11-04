package com.vasiliyplatonov.topmovieshell.controller;


import com.vasiliyplatonov.topmovieshell.entity.Movie;
import com.vasiliyplatonov.topmovieshell.service.ApplicationContextHolder;
import com.vasiliyplatonov.topmovieshell.service.movieservice.MovieService;
import com.vasiliyplatonov.topmovieshell.service.topsource.GettingTopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class KinopoiskTopByDate extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(KinopoiskTopByDate.class);

    private int topNum = 10;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean dateError = false;
        LocalDate topDate = null;
        ApplicationContext ctx = ApplicationContextHolder.getApplicationContext();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter w = resp.getWriter();


        if (req.getParameter("date") != null) {
            try {
                topDate = LocalDate.parse(req.getParameter("date"));
                // if it is future
                if (topDate.isAfter(LocalDate.now())) {
                    dateError = true;
                }
            } catch (DateTimeParseException e) {
                dateError = true;
            }
        } else {
            topDate = LocalDate.now();
        }

        List<Movie> movies = null;
        try {
            movies = ctx.getBean(MovieService.class)
                    .getFirstNByDate(topDate, topNum);
        } catch (GettingTopException e) {
            LOG.error(e.getMessage(), e);
        }

        w.println("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>10 лучших фильмов</title>\n" +
                "    <style>\n" +
                "\n" +
                "        *, ::after, ::before {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            width: 1028px;\n" +
                "            padding-right: 15px;\n" +
                "            padding-left: 15px;\n" +
                "            margin-right: auto;\n" +
                "            margin-left: auto;\n" +
                "        }\n" +
                "\n" +
                "        .row {\n" +
                "            display: -webkit-box;\n" +
                "            display: -ms-flexbox;\n" +
                "            display: flex;\n" +
                "            -ms-flex-wrap: wrap;\n" +
                "            flex-wrap: wrap;\n" +
                "            margin-right: -15px;\n" +
                "            margin-left: -15px;\n" +
                "        }\n" +
                "\n" +
                "        .top__table {\n" +
                "            width: 100%;\n" +
                "            padding: 1.5rem;\n" +
                "            margin-right: 0;\n" +
                "            margin-left: 0;\n" +
                "            border-width: .2rem;\n" +
                "        }\n" +
                "\n" +
                "        h1 {text-align: center;\n" +
                "            font-size: 2.5rem;\n" +
                "            margin-bottom: .5rem;\n" +
                "            font-family: inherit;\n" +
                "            font-weight: 500;\n" +
                "            line-height: 1.2;\n" +
                "            color: inherit;\n" +
                "        }\n" +
                "\n" +
                "        .table {\n" +
                "            width: 100%;\n" +
                "            max-width: 100%;\n" +
                "            margin-bottom: 1rem;\n" +
                "            background-color: transparent;\n" +
                "        }\n" +
                "\n" +
                "        .table td, .table th {\n" +
                "            padding: .75rem;\n" +
                "            vertical-align: top;\n" +
                "            border-top: 1px solid #dee2e6;\n" +
                "        }\n" +
                "\n" +
                "        .table .thead-blue th {\n" +
                "            color: #fff;\n" +
                "            background-color: #007bff;\n" +
                "            border-color: #007bff;\n" +
                "        }\n" +
                "\n" +
                "        .table-striped tbody tr:nth-of-type(odd) {\n" +
                "            background-color: rgba(0, 0, 0, .05);\n" +
                "        }\n" +
                "\n" +
                "        .form-group {\n" +
                "            margin-bottom: 1rem;\n" +
                "        }\n" +
                "\n" +
                "        .col-form-label {\n" +
                "            padding-top: calc(.375rem + 1px);\n" +
                "            padding-bottom: calc(.375rem + 1px);\n" +
                "            margin-bottom: 0;\n" +
                "            font-size: inherit;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "\n" +
                "        .col-2 {\n" +
                "            -webkit-box-flex: 0;\n" +
                "            -ms-flex: 0 0 16.666667%;\n" +
                "            flex: 0 0 16.666667%;\n" +
                "            max-width: 16.666667%;\n" +
                "        }\n" +
                "        .col-12 {\n" +
                "            -webkit-box-flex: 0;\n" +
                "            -ms-flex: 0 0 100%;\n" +
                "            flex: 0 0 100%;\n" +
                "            max-width: 100%;\n" +
                "        }\n" +
                "        .col-6 {\n" +
                "            -webkit-box-flex: 0;\n" +
                "            -ms-flex: 0 0 50%;\n" +
                "            flex: 0 0 50%;\n" +
                "            max-width: 50%;\n" +
                "        }\n" +
                "\n" +
                "        .top__actions {\n" +
                "            width: 100%;\n" +
                "            margin-right: 0;\n" +
                "            margin-left: 0;\n" +
                "        }\n" +
                "\n" +
                "        .form-control {\n" +
                "            display: block;\n" +
                "            width: 100%;\n" +
                "            padding: .375rem .75rem;\n" +
                "            font-size: 1rem;\n" +
                "            line-height: 1.5;\n" +
                "            color: #495057;\n" +
                "            background-color: #fff;\n" +
                "            background-clip: padding-box;\n" +
                "            border: 1px solid #ced4da;\n" +
                "            border-radius: .25rem;\n" +
                "            transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;\n" +
                "        }\n" +
                "\n" +
                "        .col-form-label {\n" +
                "            padding-top: calc(.375rem + 1px);\n" +
                "            padding-bottom: calc(.375rem + 1px);\n" +
                "            margin-bottom: 0;\n" +
                "            font-size: inherit;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "\n" +
                "        .btn {\n" +
                "            display: inline-block;\n" +
                "            font-weight: 400;\n" +
                "            text-align: center;\n" +
                "            white-space: nowrap;\n" +
                "            vertical-align: middle;\n" +
                "            -webkit-user-select: none;\n" +
                "            -moz-user-select: none;\n" +
                "            -ms-user-select: none;\n" +
                "            user-select: none;\n" +
                "            padding: .375rem .75rem;\n" +
                "            font-size: 1rem;\n" +
                "            line-height: 1.5;\n" +
                "            border-radius: .25rem;\n" +
                "            transition: color .15s ease-in-out, background-color .15s ease-in-out, border-color .15s ease-in-out, box-shadow .15s ease-in-out;\n" +
                "            color: #fff;\n" +
                "            background-color: #007bff;\n" +
                "            border-color: #007bff;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        .top__actions-btn{\n" +
                "            margin-left: 20px;\n" +
                "        }" +
                "        .alert-danger {\n" +
                "            color: #721c24;\n" +
                "            background-color: #f8d7da;\n" +
                "            border-color: #f5c6cb;\n" +
                "        }\n" +
                "\n" +
                "        .alert {\n" +
                "            position: relative;\n" +
                "            padding: .75rem 1.25rem;\n" +
                "            margin-bottom: 1rem;\n" +
                "            border: 1px solid transparent;\n" +
                "            border-radius: .25rem;\n" +
                "        }\n" +

                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "      <div class=\"top\">\n" +
                "          <div class=\"row\">\n" +
                "            <h1 class=\"top__header col-12\">10 лучших фильмов по версии портала КиноПоиск</h1>\n" +
                "            <div class=\"top__date\">");
        if (topDate != null) {
            w.println(topDate);
        }
        w.println("</div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"top__table\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead class=\"thead-blue table-striped\">\n" +
                "                <tr>\n" +
                "                    <th scope=\"col\">#</th>\n" +
                "                    <th scope=\"col\">Название</th>\n" +
                "                    <th scope=\"col\">Год</th>\n" +
                "                    <th scope=\"col\">Рейтинг</th>\n" +
                "                    <th scope=\"col\">Количество голосов</th>\n" +
                "                </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n");

        if (movies != null && !movies.isEmpty() && !dateError) {
            for (int i = 0; i < movies.size(); i++) {
                            w.println("<tr><th scope=\"row\">");
                w.println(i+1);
                w.println("  </th><td>");
                w.print(movies.get(i).getTitle());
                w.println("</td><td>");
                w.println(movies.get(i).getYear());
                w.println("</td><td>");
                w.println(movies.get(i).getRating());
                w.println("</td><td>");
                w.println(movies.get(i).getVotes());
                w.println("</td></tr>");
            }
        } else if (movies == null || movies.isEmpty()) {
            w.println(
                    "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            "Ошибка при получении рейтинга фильмов или рейтинга на такую дату нет!  Воспользуйтесь доставщиком рейтинга!\n" +
                            "</div>");
        }
        w.println(
                "                </tbody>\n" +
                        "            </table>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"top__actions\">\n" +
                        "            <form action=\"KinopoiskTopByDate\" method=\"POST\">\n" +
                        "                <div class=\"form-group row\">\n" +
                        "                    <label for=\"date\" class=\"col-2 col-form-label\">Введите дату</label>\n" +
                        "                    <div class=\"col-6\">\n" +
                        "                        <input type=\"text\" class=\"form-control\" id=\"date\" name=\"date\" placeholder=\"2019-4-27\">\n" +
                        "                    </div>\n" +
                        "                    <button type=\"submit\" class=\"btn top__actions-btn col-2\">Отправить</button>\n" +
                        "                </div>\n");
        if (dateError) {
            w.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "    Неправильно введена дата!\n" +
                    "</div>");
        }
        w.println(
                "            </form>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "        </div>\n" +
                        "\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

        w.flush();
        w.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
