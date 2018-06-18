package com.inalkar.skeleton.jdk8.javafx.util.dialog;

public interface ErrorDialog extends Dialog<ErrorDialog> {
    ErrorDialog title(String title);
    ErrorDialog text(String text);
    ErrorDialog okButtonTitle(String title);
}
