package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("C:\\Programming\\Jit\\src");
        printFileTree(path.toString(), 0, false);
    }

    // add sorting by dir || not dir and then a-z


    //│   ├── index
    //│   ├── info
    //│   │   └── exclude
    //│   ├── logs
    //│   │   ├── HEAD
    //│   │   └── refs
    //│   │       ├── heads
    //│   │       │   └── master
    //│   │       └── remotes
    //│   │           └── origin
    //│   │               └── master

    public static void printFileTree(String path, int depth, boolean last)
    {
        File file = new File(path);
        File[] files = file.listFiles();

        var dirOutput = last ? addLastFileArrow(depth) : addFileArrow(depth);
        System.out.println(dirOutput + file.getName());

        if (files == null) return;
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

    private static String addFileArrow(int depth)
    {
        var sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("│   ");
        }
        sb.append("├─── ");
        return sb.toString();
    }

    private static String addLastFileArrow(int depth)
    {
        var sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("│   ");
        }
        sb.append("└─── ");
        return sb.toString();
    }

}