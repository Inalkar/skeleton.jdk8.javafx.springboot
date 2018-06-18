package com.inalkar.skeleton.jdk8.javafx.util.dialog;

public interface WarningDialog extends Dialog<WarningDialog> {
    WarningDialog title(String title);
    WarningDialog text(String text);
    WarningDialog okButtonTitle(String title);
}
