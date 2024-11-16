package org.example;

import java.io.File;
import java.nio.file.Path;

public class DirectoryManager {


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
        TreeNode fileTree = new TreeNode(file.getName(), file.isDirectory());

        if (file.isFile()) {
            return fileTree;
        }

        var children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                fileTree.addChild(buildTree(child));
            }
        }

        return fileTree;
    }


}
