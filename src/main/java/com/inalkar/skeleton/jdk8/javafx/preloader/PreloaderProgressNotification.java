package com.inalkar.skeleton.jdk8.javafx.preloader;

import javafx.application.Preloader;

public class PreloaderProgressNotification implements Preloader.PreloaderNotification {

    private String details = "Loading...";
    private double progress;


    public PreloaderProgressNotification(final double preloaderProgress, final String preloaderDescription) {
        this.progress = preloaderProgress;
        this.details = preloaderDescription;
    }

    public String getDetails() {
        return details;
    }

    public double getProgress() {
        return progress;
    }

}
