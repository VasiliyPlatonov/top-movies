package com.vasiliyplatonov.topmoviesshellapp.config;


import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.InputProvider;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jcommander.JCommanderParameterResolverAutoConfiguration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.StandardAPIAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;

@Configuration
@Import({
        SpringShellAutoConfiguration.class,
        JLineShellAutoConfiguration.class,
        JCommanderParameterResolverAutoConfiguration.class,
        StandardAPIAutoConfiguration.class,
        StandardCommandsAutoConfiguration.class,
})
public class ShellConfig {

    @Bean
    @Autowired
    public InputProvider inputProvider(LineReader lineReader, PromptProvider promptProvider) {
        return new InteractiveShellApplicationRunner.JLineInputProvider(lineReader, promptProvider);
    }

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("top-movies:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
