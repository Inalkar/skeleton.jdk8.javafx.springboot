package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import com.inalkar.skeleton.jdk8.javafx.util.dialog.event.IDialogEvent;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.event.IDialogTextEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

import java.util.Optional;

import static com.inalkar.skeleton.jdk8.javafx.Application.MAIN_STYLE;


/**
 * A small helper class for working with Alerts
 *
 * @author Svyatoslav Voloshin
 */
public abstract class DialogsUtil {

    protected Alert createStyledAlert(final Alert.AlertType alertType) {
        Dialog alert = new Alert(alertType);
        return (Alert) styleDialog(alert);
    }

    protected Dialog createStyledTextInputDialog() {
        Dialog textInputDialog = new TextInputDialog();
        return styleDialog(textInputDialog);
    }

    private Dialog styleDialog(final Dialog dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(MAIN_STYLE);
        return dialog;
    }

    private void setContentText(final String msg, final Dialog dialog) {
        if (msg != null) {
            dialog.setContentText(msg);
        }
    }

    protected void setButtons(final String okBtnTitle, final String cancelBtnTitle,
                              final Dialog textInputDialog)
    {
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType(cancelBtnTitle, ButtonBar.ButtonData.CANCEL_CLOSE);
        textInputDialog.getDialogPane().getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    }

    protected void styleDialog(final String title, final String headerText, final String msg,
                               final Dialog textInputDialog)
    {
        setTitle(title, textInputDialog);
        textInputDialog.setHeaderText(headerText);
        setContentText(msg, textInputDialog);
    }

    private void setTitle(final String title, final Dialog textInputDialog) {
        if (title != null) {
            textInputDialog.setTitle(title);
        }
    }

    protected void handleResult(final IDialogEvent onOk, final IDialogEvent onCancel,
                                final ButtonType buttonTypeOk, final Optional<ButtonType> result)
    {
        if (result.get() == buttonTypeOk) {
            if (onOk != null) {
                onOk.execute();
            }
        } else {
            if (onCancel != null) {
                onCancel.execute();
            }
        }
    }

    protected void handleResultWithText(final IDialogTextEvent onOk, final IDialogEvent onCancel,
                                        final Optional<ButtonType> result, final String text)
    {
        if (result.isPresent()) {
            if (onOk != null) {
                onOk.execute(text);
            }
        } else {
            if (onCancel != null) {
                onCancel.execute();
            }
        }
    }

    protected void handleResultWithText(final IDialogTextEvent onOk,
                                        final Optional<ButtonType> result, final String text)
    {
        if (result.isPresent()) {
            if (onOk != null) {
                onOk.execute(text);
            }
        }
    }

    protected void setTextInput(final String msg, final Dialog textInputDialog, final TextInputControl textField) {
        GridPane grid = new GridPane();
        grid.add(new Label(msg), 0, 0);
        grid.add(textField, 0, 1);
        textInputDialog.getDialogPane().setContent(grid);
    }

    protected void setTextInput(final Dialog textInputDialog, final Node component) {
        textInputDialog.getDialogPane().setContent(component);
    }

    protected void setDisplayedInfo(final Dialog dialog, final Node component) {
        dialog.getDialogPane().setContent(component);
    }

}
