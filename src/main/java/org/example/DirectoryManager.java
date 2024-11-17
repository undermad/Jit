package org.example;

import java.io.*;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DirectoryManager {

    private static FileHasher fileHasher = FileHasher.getInstance();

    public static void reconstructDirectoryTree(TreeNode tree, Path targetPath) {
        var file = targetPath.toFile();

        if (!file.isDirectory()) {
            return;
        }


    }


    public static TreeNode buildDirectoryTree(Path path) {
        return buildTree(path.toFile());
    }

    private static TreeNode buildTree(File file) {

        TreeNode fileTree;

        if (file.isFile()) {
            var hashedFileName = fileHasher.hashContent(file);
            fileTree = new TreeNode(hashedFileName, file.isDirectory());
            return fileTree;
        }

        fileTree = new TreeNode(file.getName(), file.isDirectory());
        var children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                fileTree.addChild(buildTree(child));
            }
        }

        return fileTree;
    }


}
