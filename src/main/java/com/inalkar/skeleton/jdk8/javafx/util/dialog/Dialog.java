package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.scene.layout.Pane;

interface Dialog<T extends Dialog> {
    void show(Pane parent);
    void hide();
    T onClose(IDialogEvent onCloseEvent);
}
