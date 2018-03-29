package com.inalkar.skeleton.jdk8.javafx;

import com.inalkar.skeleton.jdk8.javafx.preloader.PreloaderProgress;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Preloader;
import javafx.scene.text.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.inalkar.skeleton.jdk8.javafx.preloader.ApplicationPreloader.setPreloaderProgress;
import static com.sun.javafx.application.LauncherImpl.launchApplication;

public abstract class AbstractJavaFxApplicationSupport extends javafx.application.Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJavaFxApplicationSupport.class);
    private static String[] savedArgs;
    private static final String[] CUSTOM_FONTS = new String[] {};

    @Override
    public void init() throws Exception {
        setPreloaderProgress(PreloaderProgress.START_INIT);
        
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> LOGGER.error("Uncaught Exception in thread: " + t, e));
        
        setPreloaderProgress(PreloaderProgress.SPRING_START_INIT);
        try {
            ConfigurableApplicationContext context = SpringApplication.run(getClass(), savedArgs);
            context.getAutowireCapableBeanFactory().autowireBean(this);
        } catch (Exception e) {
            LOGGER.error("Error initializing Spring context", e);
            System.exit(0);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    protected static void launchApp(final Class<? extends AbstractJavaFxApplicationSupport> clazz,
                                    final Class<? extends Preloader> preloaderClazz, final String[] args)
    {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        SvgImageLoaderFactory.install();
        loadFonts();
        launchApplication(clazz, preloaderClazz, args);
    }

    private static void loadFonts() {
        for (String customFont : CUSTOM_FONTS) {
            try {
                loadFont(customFont);
            } catch (Exception e) {
                LOGGER.error("Error loading font: " + customFont, e);
            }
        }
    }

    private static void loadFont(final String fontName) {
        String fontPath = "styles/font/" + fontName;
        double defaultFontSize = 16;
        Font.loadFont(
                AbstractJavaFxApplicationSupport.class.getClassLoader().getResource(fontPath).toExternalForm(),
                defaultFontSize
        );
    }

}
