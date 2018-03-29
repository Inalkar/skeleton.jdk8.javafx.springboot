package com.inalkar.skeleton.jdk8.javafx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Util class to work with the File System
 *
 * @author Svyatoslav Voloshin
 */
@Component
public class FileSystemUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemUtil.class);
    
    public static Path getApplicationPath() {
        try {
            URL location = FileSystemUtil.class.getProtectionDomain().getCodeSource().getLocation();
            return Paths.get(location.toURI()).getParent().toAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
