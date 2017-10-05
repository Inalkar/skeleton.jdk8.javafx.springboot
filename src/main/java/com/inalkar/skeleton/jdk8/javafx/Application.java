package com.inalkar.skeleton.jdk8.javafx;

import com.inalkar.skeleton.jdk8.javafx.config.AppConfig;
import com.inalkar.skeleton.jdk8.javafx.main.MainWindowConfig;
import com.inalkar.skeleton.jdk8.javafx.util.dialog.ErrorDialogsUtil;
import javafx.application.Preloader;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application extends javafx.application.Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static final String MAIN_STYLE =
            Application.class.getClassLoader().getResource("styles/darcula.css").toExternalForm();

    @Override
    public void start(final Stage stage) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            List<String> params = getParameters().getUnnamed();
            MainWindowConfig window = appContext.getBean(MainWindowConfig.class);
            window.setPrimaryStage(stage);
            window.showMainWindow(params);
            notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        } catch (Exception ex) {
            new ErrorDialogsUtil().exceptionDialog(ex);
            LOG.error(ex.getLocalizedMessage(), ex);
            System.exit(1);
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
