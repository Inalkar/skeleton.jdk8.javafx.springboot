package com.inalkar.skeleton.jdk8.javafx.preloader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.ResourceBundle;

public class PreloaderController {

    @FXML
    public Label progressLabel;

    @FXML
    public ProgressBar progressBar;

    @FXML
    Label versionLabel;

    @FXML
    private ResourceBundle resources;

}
