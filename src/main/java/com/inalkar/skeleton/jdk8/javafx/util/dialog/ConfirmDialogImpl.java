/*
 * The property of SamanTree Medical SA.
 */
package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

class ConfirmDialogImpl extends DialogBase<ConfirmDialog> implements ConfirmDialog {

    @FXML private Label titleLabel;
    @FXML private Label textLabel;
    @FXML private Button okButton;
    @FXML private Button cancelButton;

    protected IDialogEvent onOk;
    protected IDialogEvent onCancel;

    ConfirmDialogImpl() {
        super();
        load();
    }

    @Override
    public ConfirmDialog title(final String title) {
        titleLabel.setText(title);
        return this;
    }

    @Override
    public ConfirmDialog text(final String text) {
        textLabel.setText(text);
        return this;
    }

    @Override
    public ConfirmDialog graphic(final Node graphic) {
        textLabel.setGraphic(graphic);
        return this;
    }

    @Override
    public ConfirmDialog okButtonTitle(final String title) {
        okButton.setText(title);
        return this;
    }

    @Override
    public ConfirmDialog cancelButtonTitle(final String title) {
        cancelButton.setText(title);
        return this;
    }

    @Override
    public ConfirmDialog onOk(final IDialogEvent event) {
        onOk = event;
        return this;
    }

    @Override
    public ConfirmDialog onCancel(final IDialogEvent event) {
        onCancel = event;
        return this;
    }

    @FXML
    void ok() {
        if (onOk != null) onOk.execute();
        hide();
    }

    @FXML
    void cancel() {
        if (onCancel != null) onCancel.execute();
        hide();
    }

    @Override
    public String template() {
        return "ConfirmDialog.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    ConfirmDialog getThis() {
        return this;
    }
}
