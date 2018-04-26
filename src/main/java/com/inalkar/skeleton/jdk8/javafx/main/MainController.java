package com.inalkar.skeleton.jdk8.javafx.main;

import com.inalkar.skeleton.jdk8.javafx.config.Messages;
import com.inalkar.skeleton.jdk8.javafx.controls.FXMLComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController extends FXMLComponent implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private Messages i18n;

    @FXML private Label helloLabel;
    @FXML private Button clickBtn;
    @FXML private TextField fillTextField;
    
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
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

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
