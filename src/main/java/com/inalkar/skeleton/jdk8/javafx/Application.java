package com.inalkar.skeleton.jdk8.javafx;

import com.inalkar.skeleton.jdk8.javafx.main.MainWindow;
import com.inalkar.skeleton.jdk8.javafx.preloader.ApplicationPreloader;
import com.inalkar.skeleton.jdk8.javafx.preloader.PreloaderProgress;
import com.inalkar.skeleton.jdk8.javafx.util.database.LiquibaseUtil;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.ErrorDialogsUtil;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;
import liquibase.exception.LiquibaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.inalkar.skeleton.jdk8.javafx.preloader.ApplicationPreloader.setPreloaderProgress;
import static javafx.application.Preloader.StateChangeNotification.Type.BEFORE_START;

@SpringBootApplication
@EnableSpringConfigured
public class Application extends AbstractJavaFxApplicationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static final String MAIN_STYLE =
            Application.class.getClassLoader().getResource("styles/darcula.css").toExternalForm();

    @Autowired
    private LiquibaseUtil liquibaseUtil;
    
    @Autowired
    private ErrorDialogsUtil errorDialogsUtil;
    
    @Autowired
    private MainWindow mainWindow;
    
    @Override
    public void start(final Stage stage) {
        CompletableFuture
        .runAsync(() -> setPreloaderProgress(PreloaderProgress.DATABASE_INIT))
        .thenRunAsync(() -> {
            try {
                liquibaseUtil.updateSystemDBSchema();
            } catch (LiquibaseException | SQLException e) {
                LOGGER.error("Error updating database schema", e);
                Platform.runLater(() -> {
                    errorDialogsUtil.exceptionDialog("Error updating database schema", e);
                    System.exit(0);
                });
            }
        })
        .thenRunAsync(() -> setPreloaderProgress(PreloaderProgress.MAIN_WINDOW_INIT))
        .thenRun(() -> {
            List<String> params = getParameters().getUnnamed();
            mainWindow.setPrimaryStage(stage);
            Platform.runLater(() -> mainWindow.showMainWindow(params));
            
            setPreloaderProgress(PreloaderProgress.DONE);
            notifyPreloader(new Preloader.StateChangeNotification(BEFORE_START));
        });
    }

    public static void main(final String[] args) {
        launchApp(Application.class, ApplicationPreloader.class, args);
    }

}
