package com.inalkar.skeleton.jdk8.javafx.util;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AsyncAppAction<T, R> {

    CompletableFuture<R> execute(T param);

}
