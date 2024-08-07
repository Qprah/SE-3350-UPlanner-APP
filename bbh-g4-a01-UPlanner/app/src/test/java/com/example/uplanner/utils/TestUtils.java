package com.example.uplanner.utils;

import com.example.uplanner.application.Services;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;


public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/myDB2.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Services.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}