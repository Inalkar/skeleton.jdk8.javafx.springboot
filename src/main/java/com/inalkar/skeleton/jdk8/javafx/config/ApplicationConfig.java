package com.inalkar.skeleton.jdk8.javafx.config;

import com.inalkar.skeleton.jdk8.javafx.main.MainWindow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("com.inalkar.skeleton.jdk8.javafx")
@Import(MainWindow.class)
public class ApplicationConfig {

    public ApplicationConfig() {
        super();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}
