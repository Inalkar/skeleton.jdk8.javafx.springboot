package com.inalkar.skeleton.jdk8.javafx.main;

import com.inalkar.skeleton.jdk8.javafx.config.Messages;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.Dialogs;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.inalkar.skeleton.jdk8.javafx.Application.MAIN_STYLE;

@Component
public class MainWindow {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindow.class);
    private static Stage windowStage;

    @Autowired
    private Messages i18n;

    @Autowired
    private MainController mainController;

    public void setPrimaryStage(final Stage stage) {
        MainWindow.windowStage = stage;
    }

    public static Stage getStage() {
        return windowStage;
    }

    public void showMainWindow(final List<String> parameters) {
        try {
            init(parameters);

            Scene scene = new Scene(mainController, 800, 600);
            scene.getStylesheets().add(MAIN_STYLE);
            windowStage.setTitle(i18n.get("main.window.title"));
            windowStage.setScene(scene);

            windowStage.setFullScreenExitHint("");
            windowStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

            windowStage.show();
        } catch (RuntimeException e) {
            LOGGER.error("An exception occurred while loading main window: " + e.getMessage(), e);
            Platform.runLater(() -> Dialogs.exceptionDialog(e));
            System.exit(0);
        }
    }

    private void init(final List<String> parameters) {

    }
}
