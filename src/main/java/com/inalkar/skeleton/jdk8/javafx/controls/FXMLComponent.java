package com.inalkar.skeleton.jdk8.javafx.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class FXMLComponent extends AnchorPane {

    public FXMLComponent() {
        super();
    }

    public abstract String template();
    protected abstract ResourceBundle getResourceBundle();

    protected void load() {
        FXMLLoader f;
        try {
            ResourceBundle resourceBundle = getResourceBundle();
            if (resourceBundle != null) {
                f = new FXMLLoader(this.getClass().getResource(template()), resourceBundle);
            } else {
                f = new FXMLLoader(this.getClass().getResource(template()));
            }
            f.setRoot(this);
            f.setController(this);
            f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
