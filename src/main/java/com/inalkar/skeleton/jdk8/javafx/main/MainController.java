package com.inalkar.skeleton.jdk8.javafx.main;

import com.inalkar.skeleton.jdk8.javafx.config.Messages;
import com.inalkar.skeleton.jdk8.javafx.controls.FXMLComponent;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.Dialogs;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.ProgressDialog;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static com.inalkar.skeleton.jdk8.javafx.util.FxUtil.runInFxThreadAndWait;
import static java.lang.Thread.sleep;
import static java.util.concurrent.CompletableFuture.runAsync;

@Component
public class MainController extends FXMLComponent implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private Messages i18n;

    @FXML private Label helloLabel;
    @FXML private Button clickBtn;
    @FXML private TextField fillTextField;

    @FXML private AnchorPane dialogsAnchorPane;
    @FXML private FlowPane dialogsFlowPane;

    @FXML private TextField infoDialogTitleField;
    @FXML private TextField infoDialogTextField;
    @FXML private TextField infoDialogButtonCaptionField;

    @FXML private TextField warningDialogTitleField;
    @FXML private TextField warningDialogTextField;
    @FXML private TextField warningDialogButtonCaptionField;

    @FXML private TextField errorDialogTitleField;
    @FXML private TextField errorDialogTextField;
    @FXML private TextField errorDialogButtonCaptionField;

    @FXML private TextField confirmDialogTitleField;
    @FXML private TextField confirmDialogTextField;
    @FXML private TextField confirmDialogOKButtonCaptionField;
    @FXML private TextField confirmDialogCancelButtonCaptionField;
    @FXML private Label confirmActionLabel;

    @FXML private TextField progressDialogTitleField;
    @FXML private TextField progressDialogTimeField;

    @Autowired
    public MainController(final Messages messages) {
        super();
        this.i18n = messages;
        load();
    }

    @FXML
    private void onBtnClick() {
        helloLabel.setText("Hello, " + fillTextField.getText() + "!");
    }

    @FXML
    private void showInfoDialog() {
        Dialogs.infoPopup()
                .title(infoDialogTitleField.getText())
                .text(infoDialogTextField.getText())
                .okButtonTitle(infoDialogButtonCaptionField.getText())
                .show(this);
    }

    @FXML
    private void showWarningDialog() {
        Dialogs.warningPopup()
                .title(warningDialogTitleField.getText())
                .text(warningDialogTextField.getText())
                .okButtonTitle(warningDialogButtonCaptionField.getText())
                .show(this);
    }

    @FXML
    private void showErrorDialog() {
        Dialogs.errorPopup()
                .title(errorDialogTitleField.getText())
                .text(errorDialogTextField.getText())
                .okButtonTitle(errorDialogButtonCaptionField.getText())
                .show(this);
    }

    @FXML
    private void showConfirmDialog() {
        Dialogs.confirmPopup()
                .title(confirmDialogTitleField.getText())
                .text(confirmDialogTextField.getText())
                .okButtonTitle(confirmDialogOKButtonCaptionField.getText())
                .cancelButtonTitle(confirmDialogCancelButtonCaptionField.getText())
                .onOk(() -> confirmActionLabel.setText("OK clicked!"))
                .onCancel(() -> confirmActionLabel.setText("Cancel clicked!"))
                .show(this);
    }

    @FXML
    private void showProgressDialog() {
        Integer parsedTime;
        try {
            parsedTime = Integer.parseInt(progressDialogTimeField.getText());
        } catch (NumberFormatException e) {
            Dialogs.errorPopup()
                .text("Please enter valid number to 'Time in seconds' field")
                .show(this);
            return;
        }

        final double time = parsedTime.doubleValue();

        final DoubleProperty progressProperty = new SimpleDoubleProperty();
        final StringProperty messageProperty = new SimpleStringProperty("");

        final ProgressDialog dialog = Dialogs.progressPopup()
                .title(progressDialogTitleField.getText())
                .progressProperty(progressProperty)
                .messageProperty(messageProperty);
        dialog.show(this);

        runAsync(() -> {
            double elapsed = 0;
            while (elapsed <= time) {
                double fElapsed = elapsed;
                runInFxThreadAndWait(() -> {
                    progressProperty.set(fElapsed / time);
                    messageProperty.set((int) fElapsed + " second(s) passed");
                });
                elapsed++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) { }
            }
            runInFxThreadAndWait(dialog::hide);
        });
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        dialogsAnchorPane.setPrefHeight(dialogsFlowPane.getHeight());
        dialogsAnchorPane.prefHeightProperty().bind(dialogsFlowPane.heightProperty());
    }

    @Override
    public String template() {
        return "MainWindow.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return i18n.getResourceBundle();
    }

}
