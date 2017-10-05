package com.inalkar.skeleton.jdk8.javafx.util.dialog.constant;

public enum MessageTitleType {
    Info("Info"),
    Error("Error"),
    Warning("Warning"),
    Confirm("Please confirm the action");

    private String value;

    MessageTitleType(final String enumValue) {
        this.value = enumValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }
}
