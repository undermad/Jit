package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.zip.Deflater;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(".\\.jit");
        Initializer.init(path);



        Path filePath = Path.of(".\\src\\main\\java\\org\\example\\Dummy.java");
        byte[] blob = Files.readAllBytes(filePath);

        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        deflater.setInput(blob);
        deflater.finish();
        byte[] compressedData = new byte[blob.length];
        int compressedDataLength = deflater.deflate(compressedData);
        byte[] output = Arrays.copyOf(compressedData, compressedDataLength);

        System.out.println("Bytes before compression: " + blob.length);
        System.out.println("Bytes after compression: " + output.length);




        Path outputPath = Path.of(".\\src\\main\\java\\org\\compressed");

        Files.write(outputPath, output);


    }


}