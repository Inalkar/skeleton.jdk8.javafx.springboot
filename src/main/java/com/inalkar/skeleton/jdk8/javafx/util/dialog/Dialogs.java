package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.inalkar.skeleton.jdk8.javafx.Application.MAIN_STYLE;

public class Dialogs {

    public static InfoDialogImpl infoPopup() {
        return new InfoDialogImpl();
    }

    public static WarningDialog warningPopup() {
        return new WarningDialogImpl();
    }

    public static ConfirmDialog confirmPopup() {
        return new ConfirmDialogImpl();
    }

    public static ErrorDialog errorPopup() {
        return new ErrorDialogImpl();
    }
    
    public static ProgressDialog progressPopup() {
        return new ProgressDialogImpl();
    }

    private static Dialog styleDialog(final Dialog dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(MAIN_STYLE);
        return dialog;
    }

    private static Alert createStyledAlert(final Alert.AlertType alertType) {
        Dialog alert = new Alert(alertType);
        return (Alert) styleDialog(alert);
    }

    public static void exceptionDialog(final Throwable ex) {
        exceptionDialog("", ex);
    }

    public static void exceptionDialog(final String message, final Throwable ex) {
        Alert alert = createStyledAlert(Alert.AlertType.ERROR);
        alert.setTitle("Unexpected Error");
        alert.setHeaderText("An unexpected error has occurred");
        alert.setContentText(message + " " + ex.getLocalizedMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

}
