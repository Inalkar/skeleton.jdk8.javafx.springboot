package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import com.inalkar.skeleton.jdk8.javafx.util.dialog.constant.ActionType;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.constant.MessageTitleType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class ErrorDialogsUtil extends DialogsUtil {

    public void error(final String msg) {
        error(MessageTitleType.Error.getValue(), null, msg, ActionType.OK.getValue(), null);
    }

    public void error(final String headerText, final String msg) {
        error(MessageTitleType.Error.getValue(), headerText, msg, ActionType.OK.getValue(), null);
    }

    public void errorAndCancel(final String msg) {
        error(MessageTitleType.Error.getValue(), null, msg, ActionType.OK.getValue(), ActionType.Cancel.getValue());
    }

    public void error(final String title, final String headerText, final String msg, final String okBtnTitle,
                      final String cancelBtnTitle)
    {
        Alert alert = createStyledAlert(Alert.AlertType.ERROR);
        styleDialog(title, headerText, msg, alert);
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        if (cancelBtnTitle != null && !cancelBtnTitle.isEmpty()) {
            ButtonType buttonTypeCancel = new ButtonType(cancelBtnTitle, ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
        } else {
            alert.getButtonTypes().setAll(buttonTypeOk);
        }
        alert.showAndWait();
    }

    public void exceptionDialog(final Exception ex) {
        exceptionDialog("", ex);
    }

    public void exceptionDialog(final String message, final Throwable ex) {
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
