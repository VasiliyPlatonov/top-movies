package com.vasiliyplatonov.topmovieshell;


import com.vasiliyplatonov.topmoviesfetcher.config.HibernateConfig;
import com.vasiliyplatonov.topmoviesfetcher.repository.MovieRepository;
import com.vasiliyplatonov.topmoviesfetcher.repository.MovieRepositoryJpa;
import com.vasiliyplatonov.topmoviesfetcher.service.MovieTopFetcher;
import com.vasiliyplatonov.topmoviesfetcher.service.MovieTopFetcherImpl;
import com.vasiliyplatonov.topmoviesfetcher.service.mapper.KinopoiskTopMapper;
import com.vasiliyplatonov.topmoviesfetcher.service.mapper.MovieMapper;
import com.vasiliyplatonov.topmoviesfetcher.service.topsource.KinopoiskTop;
import com.vasiliyplatonov.topmoviesfetcher.service.topsource.TopSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jcommander.JCommanderParameterResolverAutoConfiguration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.StandardAPIAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan
@Import({
        SpringShellAutoConfiguration.class,
        JLineShellAutoConfiguration.class,
        JCommanderParameterResolverAutoConfiguration.class,
        StandardAPIAutoConfiguration.class,
        StandardCommandsAutoConfiguration.class,
})
@PropertySource("classpath:app.properties")
@Lazy
public class TopMoviesShell extends HibernateConfig {

    private Environment env;

    @Autowired
    public TopMoviesShell(Environment env) {
        super(env);
        this.env = env;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TopMoviesShell.class);
        Shell shell = context.getBean(Shell.class);
        shell.run(context.getBean(InputProvider.class));
    }


    @Bean
    @Autowired
    public InputProvider inputProvider(LineReader lineReader, PromptProvider promptProvider) {
        return new InteractiveShellApplicationRunner.JLineInputProvider(lineReader, promptProvider);
    }

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("top-movies:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }

    @Lazy
    @Bean("fetcher")
    public MovieTopFetcher KinopoiskTopFetcher() {
        return new MovieTopFetcherImpl(topSource(), mapper(), movieRepo());
    }

    @Lazy
    @Bean
    public TopSource<Elements> topSource() {
        return new KinopoiskTop();
    }

    @Lazy
    @Bean
    public MovieMapper<Element> mapper() {
        return new KinopoiskTopMapper();
    }

    @Lazy
    @Bean
    public MovieRepository movieRepo() {
        return new MovieRepositoryJpa();
    }

    @Lazy
    @Bean
    @Override
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("url.tcp.server"));
        dataSource.setUsername(env.getRequiredProperty("username"));
        dataSource.setPassword(env.getRequiredProperty("password"));
        return dataSource;
    }

}
