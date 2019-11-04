package com.vasiliyplatonov.topmoviesshellapp.command;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.PreDestroy;
import java.sql.SQLException;

@ShellComponent
public class H2TcpServerCommands {
    private static final Logger LOG = LoggerFactory.getLogger(H2TcpServerCommands.class);

    private Server tcpServer;

    public H2TcpServerCommands() {
        this.tcpServer = tcpServer;
        try {
            tcpServer =
                    Server.createTcpServer(
                            "-tcp",
                            "-tcpAllowOthers",
                            "-ifNotExists",
                            "-tcpPort",
                            "9090");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    @ShellMethod("Stop the h2 database server.")
    public void stop() {
        if (isRunning(false)) {
            tcpServer.stop();
            LOG.info("The tcp h2 database server on the port 9090 has been stopped.");
        }else {
            LOG.info("The tcp h2 database server has not been started.");
        }
    }

    @ShellMethod("Start the h2 database server.")
    public void start() {
        Server server = null;
        try {
            server = tcpServer.start();
        } catch (SQLException e) {
            LOG.error("An exception occurred while trying to starting h2 database server. " +
                    "Make sure port 9090 is not in use and try again.", e);
        }
        if (server != null) {
            LOG.info("On the port 9090 has been started tcp h2 database server.\n " +
                    "If you want to stop server, please write 'stop'.");
        }
    }

    /**
     * Checks if the server is running.
     *
     * @param traceError if errors should be written
     * @return if the server is running
     */
    public boolean isRunning(boolean traceError) {
        return tcpServer.isRunning(traceError);
    }
}
