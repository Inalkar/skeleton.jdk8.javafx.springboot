package com.inalkar.skeleton.jdk8.javafx.util.dialog.constant;

public enum ActionType {
    OK("OK"),
    Confirm("Confirm"),
    Continue("Continue"),
    Cancel("Cancel"),
    Close("Close"),
    Save("Save");

    private String value;

    ActionType(final String actionTypeValue) {
        this.value = actionTypeValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }
}
