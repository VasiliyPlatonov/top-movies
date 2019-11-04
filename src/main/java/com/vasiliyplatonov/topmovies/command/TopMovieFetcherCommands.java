package com.vasiliyplatonov.topmovies.command;


import com.vasiliyplatonov.topmovies.service.fetcher.MovieTopFetcher;
import com.vasiliyplatonov.topmovies.service.topsource.GettingTopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;


@ShellComponent("fetcherCommands")
public class TopMovieFetcherCommands {
    private static final Logger LOG = LoggerFactory.getLogger(H2TcpServerCommands.class);


    private final MovieTopFetcher fetcher;
    private final H2TcpServerCommands h2Server;


    @Autowired
    public TopMovieFetcherCommands(@Lazy MovieTopFetcher fetcher, H2TcpServerCommands h2Server) {
        this.fetcher = fetcher;
        this.h2Server = h2Server;
    }


    @ShellMethod("Fetch the top of the movies by date and save it in database. " +
            "Default date is today. Date should set like '2019-4-23. The date can't be in the future.")
    public void getTop(@ShellOption(defaultValue = "") LocalDate topDate) throws GettingTopException {
        if (!h2Server.isRunning(false)) {
            LOG.info("To getting the top of movies you should start the database server. Write the command 'start' for that.");
            return;
        }

        if (topDate == null) {
            return;
        }

        if (topDate.isAfter(LocalDate.now())) {
            LOG.info("Incorrectly top date entered. The date can't be in the future.");
        }

        fetcher.save(fetcher.fetchTopByDate(topDate));
        LOG.info("Top of the movies by '" + topDate + "' have been saved in database.");
    }
}
