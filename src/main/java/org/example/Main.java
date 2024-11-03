package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("C:\\Programming\\Jit\\src");
        printFileTree(path.toString(), 0);
    }

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

    public static void printFileTree(String path, int depth)
    {
        File file = new File(path);
        File[] files = file.listFiles();

        System.out.println(addFileArrow(depth) + file.getName());

        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            var f = files[i];


            if (f.isFile()) {
                if (i == files.length - 1) System.out.println(addLastFileArrow(depth) + f.getName());
                else System.out.println(addFileArrow(depth) + f.getName());
            } else if (f.isDirectory()) {
                printFileTree(f.getPath(), depth + 1);
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