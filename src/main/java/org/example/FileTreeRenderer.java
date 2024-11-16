package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTreeRenderer {

    public static void printFileTree(String path, int depth, boolean last) {
        File file = new File(path);
        File[] files = file.listFiles();

        var dirOutput = last ? addLastFileArrow(depth) : addFileArrow(depth);
        System.out.println(dirOutput + file.getName());

        if (files == null) return;
        files = orderDirToFiles(files);

        for (int i = 0; i < files.length; i++) {
            var f = files[i];

            if (f.isFile()) {
                var output = i == files.length - 1 ? addLastFileArrow(depth + 1) : addFileArrow(depth + 1);
                System.out.println(output + f.getName());
            } else if (f.isDirectory()) {
                var l = i == files.length - 1;
                printFileTree(f.getPath(), depth + 1, l);
            }
        }
    }

    private static File[] orderDirToFiles(File[] files) {
        var sortedFiles = new File[files.length];

        int j = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                sortedFiles[j] = file;
                j++;
            }
        }

        for (File file : files) {
            if (file.isFile()) {
                sortedFiles[j] = file;
                j++;
            }
        }

        return sortedFiles;
    }


    private static String addFileArrow(int depth) {
        return "│  ".repeat(Math.max(0, depth)) + "├─ ";
    }

    private static String addLastFileArrow(int depth) {
        return "│  ".repeat(Math.max(0, depth)) + "└─ ";
    }

    public static class SimpleVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {


            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file.getFileName());
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return super.visitFileFailed(file, exc);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return super.postVisitDirectory(dir, exc);
        }


    }



}
