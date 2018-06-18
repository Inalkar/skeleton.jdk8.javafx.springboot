package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import com.inalkar.skeleton.jdk8.javafx.controls.FXMLComponent;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

abstract class DialogBase<T extends Dialog> extends FXMLComponent implements Dialog<T> {

    private Pane currentParent;
    private IDialogEvent onClose;

    abstract T getThis();

    @Override
    public void show(final Pane parent) {
        currentParent = parent;
        setLayoutX(parent.getLayoutX());
        parent.layoutXProperty().addListener((obs, o, n) -> setLayoutX(n.doubleValue()));

        setLayoutY(parent.getLayoutY());
        parent.layoutYProperty().addListener((obs, o, n) -> setLayoutY(n.doubleValue()));

        setMaxHeight(parent.getHeight());
        setMinHeight(parent.getHeight());
        parent.heightProperty().addListener((obs, o, n) -> {
            setMaxHeight(n.doubleValue());
            setMinHeight(n.doubleValue());
        });

        setMaxWidth(parent.getWidth());
        setMinWidth(parent.getWidth());
        parent.widthProperty().addListener((obs, o, n) -> {
            setMaxWidth(n.doubleValue());
            setMinWidth(n.doubleValue());
        });
        Platform.runLater(() -> {
            parent.getChildren().add(this);
            setVisible(true);
        });
    }

    @Override
    public T onClose(final IDialogEvent onCloseEvent) {
        this.onClose = onCloseEvent;
        return getThis();
    }

    public void hide() {
        Platform.runLater(() -> {
            if (onClose != null) onClose.execute();
            if (currentParent != null) currentParent.getChildren().remove(this);
            currentParent = null;
        });
    }

}
