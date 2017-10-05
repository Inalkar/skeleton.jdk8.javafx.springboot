package com.inalkar.skeleton.jdk8.javafx.main;

import com.inalkar.skeleton.jdk8.javafx.util.dialog.ErrorDialogsUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.List;

import static com.inalkar.skeleton.jdk8.javafx.Application.MAIN_STYLE;

@Configuration
@Lazy
public class MainWindowConfig {

    private static final Logger LOG = LogManager.getLogger(MainWindowConfig.class);
    private static final int MAIN_WINDOW_WIDTH = 800;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    private static final String MAIN_WINDOW_TITLE = "JavaFX Application";
    private static Stage windowStage;

    @Autowired
    private MainController mainController;

    @Autowired
    private ErrorDialogsUtil errorDialogsUtil;

    public void setPrimaryStage(final Stage stage) {
        MainWindowConfig.windowStage = stage;
    }

    public static Stage getStage() {
        return windowStage;
    }

    public void showMainWindow(final List<String> parameters) {
        Parent root = null;
        try {
            init(parameters);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainWindow.fxml"));
            loader.setController(mainController);
            root = loader.load();
        } catch (IOException e) {
            LOG.error("An exception occurred while load int main window: " + e.getLocalizedMessage(), e);
            errorDialogsUtil.exceptionDialog(e);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(MAIN_STYLE);
        windowStage.setMinWidth(MAIN_WINDOW_WIDTH);
        windowStage.setMinHeight(MAIN_WINDOW_HEIGHT);
        windowStage.setTitle(MAIN_WINDOW_TITLE);
        windowStage.setScene(scene);
        windowStage.show();
    }

    private void init(final List<String> parameters) {

    }
}
