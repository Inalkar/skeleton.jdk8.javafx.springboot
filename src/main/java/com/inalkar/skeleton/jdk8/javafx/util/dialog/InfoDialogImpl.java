package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

class InfoDialogImpl extends DialogBase<InfoDialog> implements InfoDialog {

    @FXML private Label titleLabel;
    @FXML private Label textLabel;
    @FXML private Button okButton;

    InfoDialogImpl() {
        super();
        load();
    }

    @Override
    public InfoDialog title(final String title) {
        titleLabel.setText(title);
        return this;
    }

    @Override
    public InfoDialog text(final String text) {
        textLabel.setText(text);
        return this;
    }

    @Override
    public InfoDialog okButtonTitle(final String title) {
        okButton.setText(title);
        return this;
    }

    @FXML
    void ok() {
        hide();
    }

    @Override
    public String template() {
        return "InfoDialog.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    InfoDialog getThis() {
        return this;
    }
}
