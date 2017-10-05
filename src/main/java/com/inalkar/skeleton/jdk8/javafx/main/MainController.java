package com.inalkar.skeleton.jdk8.javafx.main;

import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {

    private static final Logger LOG = LogManager.getLogger(MainController.class);

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

}
