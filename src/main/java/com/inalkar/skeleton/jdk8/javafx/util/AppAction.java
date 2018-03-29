package com.inalkar.skeleton.jdk8.javafx.util;

@FunctionalInterface
public interface AppAction<T> {

    void execute(T param);

}
