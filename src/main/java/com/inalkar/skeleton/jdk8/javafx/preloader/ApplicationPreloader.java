package com.inalkar.skeleton.jdk8.javafx.preloader;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ResourceBundle;

import static com.inalkar.skeleton.jdk8.javafx.Application.MAIN_STYLE;


public class ApplicationPreloader extends Preloader {

    private Stage preloaderStage;
    private PreloaderController controller;

    private static ApplicationPreloader preloader;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        preloader = this;
        this.preloaderStage = primaryStage;
        this.controller = new PreloaderController();

        String resourceBundle = "application";
        String profiles = System.getProperty("spring.profiles.active");
        if (profiles != null && profiles.contains("local")) {
            resourceBundle = "application-local";
        }
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Preloader.fxml"), bundle);
        loader.setController(controller);
        root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(MAIN_STYLE);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.setScene(scene);
        preloaderStage.setTitle(bundle.getString("app.name"));
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(final PreloaderNotification info) {
        if (info instanceof StateChangeNotification) {
            preloaderStage.close();
        }
        if (info instanceof PreloaderProgressNotification) {
            PreloaderProgressNotification progressNotification = (PreloaderProgressNotification) info;
            Platform.runLater(() -> {
                controller.progressLabel.setText(progressNotification.getDetails());
                controller.progressBar.setProgress(progressNotification.getProgress());
            });
        }
    }

    public static void setPreloaderProgress(final PreloaderProgress progress) {
        preloader.handleApplicationNotification(
                new PreloaderProgressNotification(progress.getProgress(), progress.getDescription())
        );
    }

}
