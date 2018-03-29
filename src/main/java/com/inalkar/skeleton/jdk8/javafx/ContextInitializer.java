package com.inalkar.skeleton.jdk8.javafx;

import com.inalkar.skeleton.jdk8.javafx.preloader.PreloaderProgress;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

import static com.inalkar.skeleton.jdk8.javafx.preloader.ApplicationPreloader.setPreloaderProgress;
import static com.inalkar.skeleton.jdk8.javafx.util.FileSystemUtil.getApplicationPath;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(final ConfigurableApplicationContext appCtx) {
        setPreloaderProgress(PreloaderProgress.SPRING_CONTEXT_INIT);

        // Create database in application folder
        String appPath = getApplicationPath().toString();
        Map<String, Object> props = new HashMap<>();
        props.put("spring.datasource.url", "jdbc:h2:file:" + appPath + "/db;AUTO_SERVER=TRUE");
        MapPropertySource mapPropertySource = new MapPropertySource("db-props", props);
        appCtx.getEnvironment().getPropertySources().addFirst(mapPropertySource);

        setPreloaderProgress(PreloaderProgress.SPRING_CONTEXT_INIT2);
    }

}
