package com.vasiliyplatonov.topmoviesshellapp.command;

import com.vasiliyplatonov.topmoviesshellapp.controller.KinopoiskTopByDate;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class WebCommand {
    private static final Logger LOG = LoggerFactory.getLogger(WebCommand.class);

    private final Tomcat tomcat;
    private final Context tomcatCtx;

    @Autowired
    public WebCommand(Tomcat tomcat, Context tomcatCtx) {
        this.tomcat = tomcat;
        this.tomcatCtx = tomcatCtx;
    }


    @ShellMethod("Start web-server.")
    public void webStart() {
        final String servletName = "KinopoiskTopByDate";
        final String urlPattern = "/*";

        tomcat.addServlet(tomcatCtx.getPath(), servletName, new KinopoiskTopByDate());
        tomcatCtx.addServletMappingDecoded(urlPattern, servletName);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            LOG.error(e.getMessage(), e);
        }

        LOG.info("The Tomcat has been started by host.");
    }

    @ShellMethod("Stop web-server.")
    public void webStop() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("The Tomcat servlet container has been stopped.");
    }
}
