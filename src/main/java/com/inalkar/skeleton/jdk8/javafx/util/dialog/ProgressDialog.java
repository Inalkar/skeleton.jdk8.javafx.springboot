package com.inalkar.skeleton.jdk8.javafx.util.dialog;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public interface ProgressDialog extends Dialog<ProgressDialog> {
    ProgressDialog title(String title);
    ProgressDialog messageProperty(StringProperty text);
    ProgressDialog progressProperty(DoubleProperty progressProperty);
}
