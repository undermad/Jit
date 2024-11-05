package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Initializer {

    public static void init(Path root) {
        initJitDirectory(root);
        initObjectsDirectory(root);
    }

    public static void initJitDirectory(Path root) {
        try {
            if (Files.notExists(root)) {
                Files.createDirectory(root);
                System.out.println("Created directory.");
            }
        } catch (IOException e) {
            System.out.println("Can not create main directory");
            throw new RuntimeException(e);
        }
    }

    public static void initObjectsDirectory(Path root) {
        try {
            Path objectDirectory = root.resolve("objects");
            if (Files.notExists(objectDirectory)) {
                Files.createDirectory(objectDirectory);
                System.out.println("Object directory created");
            }
        } catch (IOException e) {
            System.out.println("Can not create objects directory.");
            throw new RuntimeException(e);
        }
    }

}
