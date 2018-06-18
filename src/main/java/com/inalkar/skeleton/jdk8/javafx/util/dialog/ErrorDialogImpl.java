package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

class ErrorDialogImpl extends DialogBase<ErrorDialog> implements ErrorDialog {

    @FXML private Label titleLabel;
    @FXML private Label textLabel;
    @FXML private Button okButton;

    ErrorDialogImpl() {
        super();
        load();
    }

    @Override
    public ErrorDialog title(final String title) {
        titleLabel.setText(title);
        return this;
    }

    @Override
    public ErrorDialog text(final String text) {
        textLabel.setText(text);
        return this;
    }

    @Override
    public ErrorDialog okButtonTitle(final String title) {
        okButton.setText(title);
        return this;
    }

    @FXML
    void ok() {
        hide();
    }

    @Override
    public String template() {
        return "ErrorDialog.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    ErrorDialog getThis() {
        return this;
    }
    
}
