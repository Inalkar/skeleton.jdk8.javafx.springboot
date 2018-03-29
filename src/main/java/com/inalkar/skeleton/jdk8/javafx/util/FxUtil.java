package com.inalkar.skeleton.jdk8.javafx.util;

import javafx.application.Platform;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class FxUtil {

    public static void runInFxThreadAndWait(final Runnable action) {
        if (action == null) throw new NullPointerException();

        // run synchronously on JavaFX thread
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }

        // queue on JavaFX thread and wait for completion
        final CountDownLatch doneLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                doneLatch.countDown();
            }
        });

        try {
            doneLatch.await();
        } catch (InterruptedException e) {
            // ignore exception
        }
    }

}
