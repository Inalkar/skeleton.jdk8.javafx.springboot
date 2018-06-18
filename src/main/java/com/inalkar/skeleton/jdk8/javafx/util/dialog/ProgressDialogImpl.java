package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.animation.PauseTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgressDialogImpl extends DialogBase<ProgressDialog> implements ProgressDialog {

    @FXML private Label titleLabel;
    @FXML private Label textLabel;
    @FXML private ProgressBar progressBar;
    
    private StringProperty messageProperty = new SimpleStringProperty();
    private AtomicInteger counter = new AtomicInteger();
    private PauseTransition delayTimer = new PauseTransition(Duration.seconds(1));

    ProgressDialogImpl() {
        super();
        load();
        messageProperty.addListener((obs, o, n) -> {
            counter.set(0);
            textLabel.setText(n);
            delayTimer.playFromStart();
        });
        delayTimer.setOnFinished(event -> {
            delayTimer.playFromStart();
            int dotsCount = counter.incrementAndGet();
            if (dotsCount == 3) counter.set(0);
            String dots = Stream.generate(() -> ".").limit(dotsCount).collect(Collectors.joining());
            textLabel.setText(messageProperty.getValue() + " " + dots);
        });
    }

    @Override
    public ProgressDialog title(final String title) {
        titleLabel.setText(title);
        return this;
    }

    @Override
    public ProgressDialog messageProperty(final StringProperty text) {
        messageProperty.bind(text);
        return this;
    }

    @Override
    public ProgressDialog progressProperty(final DoubleProperty progressProperty) {
        progressBar.progressProperty().bind(progressProperty);
        return this;
    }

    @Override
    public void hide() {
        delayTimer.stop();
        counter.set(0);
        messageProperty.unbind();
        progressBar.progressProperty().unbind();
        super.hide();
    }

    @Override
    public String template() {
        return "ProgressDialog.fxml";
    }

    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    ProgressDialog getThis() {
        return this;
    }
}
