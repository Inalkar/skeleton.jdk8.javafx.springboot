package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.scene.Node;

public interface ConfirmDialog extends Dialog<ConfirmDialog> {
    ConfirmDialog title(String title);
    ConfirmDialog text(String text);
    ConfirmDialog graphic(Node graphic);
    ConfirmDialog okButtonTitle(String title);
    ConfirmDialog cancelButtonTitle(String title);
    ConfirmDialog onOk(IDialogEvent event);
    ConfirmDialog onCancel(IDialogEvent event);
}
