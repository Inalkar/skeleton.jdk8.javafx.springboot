package com.inalkar.skeleton.jdk8.javafx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class Messages {

    @Value("${app.locale}")
    private String currentLocale;

    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor accessor;
    private ResourceBundle resourceBundle;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, getCurrentLocale());
        resourceBundle = ResourceBundle.getBundle("messages", getCurrentLocale());
    }

    public String get(final String code) {
        return accessor.getMessage(code);
    }

    public Locale getCurrentLocale() {
        return new Locale(currentLocale);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
