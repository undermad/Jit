package org.example;

import java.io.IOException;
import java.nio.file.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("C:\\Programming\\Jit\\src");
        FileTreeRenderer.printFileTree(path.toString(), 0, false);
    }


}