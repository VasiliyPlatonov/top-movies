package com.vasiliyplatonov.topmoviesweb.controller;


import com.vasiliyplatonov.topmoviesweb.domain.Movie;
import com.vasiliyplatonov.topmoviesweb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class KinopoiskTopByDate extends HttpServlet {

    private ApplicationContext appCxt;

    @Autowired
    public KinopoiskTopByDate(ApplicationContext appCxt) {
        this.appCxt = appCxt;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter w = resp.getWriter();
        HttpSession session = req.getSession();

//        if (session.getAttribute("mService") == null) {
//            MovieService movieService = new MovieServiceImpl(
//                    new KinopoiskTop(),
//                    new KinopoiskTopMapper(),
//                    new MovieRepositoryJpa());
//
//            session.setAttribute("mService", movieService);
//        }

//        List<Movie> movies = ((MovieService) session.getAttribute("mService")).fetchTop();

        LocalDate date = LocalDate.now();
        List<Movie> movies = appCxt.getBean(MovieService.class).getTenByDate(date);


        w.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
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
                "\n" +
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
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"top\">\n" +
                "            <h1 class=\"top__header\">10 лучших фильмов по версии портала КиноПоиск</h1>\n" +
                "            <div class=\"top__date\">DATE</div>\n" +
                "        </div>\n" +
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

        for (Movie movie : movies) {
            w.println("<tr><th scope=\"row\">");
            w.print(movie.getId());
            w.println("  </th><td>");
            w.print(movie.getTitle());
            w.println("</td><td>");
            w.println(movie.getYear());
            w.println("</td><td>");
            w.println(movie.getRating());
            w.println("</td><td>");
            w.println(movie.getVotes());
            w.println("</td></tr>");
        }

        w.println(
                "                </tbody>\n" +
                        "            </table>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"top__actions\">\n" +
                        "            <form action=\"KinopoiskTopByDate\">\n" +
                        "                <div class=\"form-group row\">\n" +
                        "                    <label for=\"date\" class=\"col-2 col-form-label\">Введите дату</label>\n" +
                        "                    <div class=\"col-6\">\n" +
                        "                        <input type=\"text\" class=\"form-control\" id=\"date\" placeholder=\"2019-4-27\">\n" +
                        "                    </div>\n" +
                        "                    <button type=\"submit\" class=\"btn top__actions-btn col-2\">Отправить</button>\n" +
                        "                </div>\n" +
                        "            </form>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

        w.flush();
        w.close();
    }
}
