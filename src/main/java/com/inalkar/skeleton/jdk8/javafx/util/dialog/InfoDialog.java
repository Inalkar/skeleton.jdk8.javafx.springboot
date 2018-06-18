package com.inalkar.skeleton.jdk8.javafx.util.dialog;

public interface InfoDialog extends Dialog<InfoDialog> {
    InfoDialog title(String title);
    InfoDialog text(String text);
    InfoDialog okButtonTitle(String title);
}
