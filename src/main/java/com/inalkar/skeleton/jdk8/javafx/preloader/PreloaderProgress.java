package com.inalkar.skeleton.jdk8.javafx.preloader;

public enum PreloaderProgress {
    START_INIT(0.0, "Initializing application..."),
    SPRING_START_INIT(0.1, "Initializing context..."),
    SPRING_CONTEXT_INIT(0.2, "Determining database location..."),
    SPRING_CONTEXT_INIT2(0.3, "Initializing context..."),
    DATABASE_INIT(0.5, "Initialize database..."),
    MAIN_WINDOW_INIT(0.9, "Initialize main window..."),
    DONE(1.0, "Done...");


    private double progress;
    private String description;

    PreloaderProgress(final double preloaderProgress, final String preloaderDescription) {
        this.progress = preloaderProgress;
        this.description = preloaderDescription;
    }

    public double getProgress() {
        return progress;
    }

    public String getDescription() {
        return description;
    }
}
