package org.example;

import java.io.File;

public class FileTreeRenderer
{

    public static void printFileTree(String path, int depth, boolean last)
    {
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

    private static File[] orderDirToFiles(File[] files)
    {
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


    private static String addFileArrow(int depth)
    {
        return "│  ".repeat(Math.max(0, depth)) + "├─ ";
    }

    private static String addLastFileArrow(int depth)
    {
        return "│  ".repeat(Math.max(0, depth)) + "└─ ";
    }
}
