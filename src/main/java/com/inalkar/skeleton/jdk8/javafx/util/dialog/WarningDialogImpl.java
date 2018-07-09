package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

class WarningDialogImpl extends DialogBase<WarningDialog> implements WarningDialog {

    @FXML private Label titleLabel;
    @FXML private Label textLabel;
    @FXML private Button okButton;

    WarningDialogImpl() {
        super();
        load();
    }

    @Override
    public WarningDialog title(final String title) {
        titleLabel.setText(title);
        return this;
    }

    @Override
    public WarningDialog text(final String text) {
        textLabel.setText(text);
        return this;
    }

    @Override
    public WarningDialog okButtonTitle(final String title) {
        okButton.setText(title);
        return this;
    }

    @FXML
    void ok() {
        hide();
    }

    @Override
    public String template() {
        return "WarningDialog.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    WarningDialog getThis() {
        return this;
    }
}
