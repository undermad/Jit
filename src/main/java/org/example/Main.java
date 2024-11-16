package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(".\\.jit");
        Initializer.init(path);
//        performDummySerialization();


        var tree = DirectoryManager.buildDirectoryTree(Path.of("."));


        System.out.println(tree);



    }

    public static void performDummySerialization() {
        Path filePath = Path.of(".\\src\\main\\java\\org\\example\\Dummy.java");
        Path compressedFilePath = Path.of(".\\.jit\\objects\\dummyV1.bin");

        compressDummyFile(filePath, compressedFilePath);


        var outputFile = Path.of(".\\src\\main\\java\\org\\");
        decompressDummyFile(compressedFilePath, outputFile);
    }

    public static void decompressDummyFile(Path filePath, Path outputPath) {
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(filePath))) {
            int fileNameLength = dis.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dis.readFully(fileNameBytes);
            String originalFileName = new String(fileNameBytes);

            var outputPatha = Path.of(outputPath.toString(), originalFileName);
            try (FileOutputStream fos = new FileOutputStream(outputPatha.toFile())) {

                while (dis.available() > 0) {

                    int compressedDataLength = dis.readInt();
                    byte[] compressedChunk = new byte[compressedDataLength];
                    dis.readFully(compressedChunk);

                    Inflater inflater = new Inflater();
                    inflater.setInput(compressedChunk, 0, compressedDataLength);

                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        while (!inflater.finished()) {
                            int count = inflater.inflate(buffer);
                            baos.write(buffer, 0, count);
                        }
                        inflater.end();

                        fos.write(baos.toByteArray());
                    }
                }
            }
        } catch (DataFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void compressDummyFile(Path filePath, Path outputPath) {
        try {

            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputPath.toFile()))) {
                var originalFileName = filePath.getFileName().toString();
                dos.writeInt(originalFileName.length());
                dos.writeBytes(originalFileName);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
                    deflater.setInput(buffer, 0, bytesRead);
                    deflater.finish();

                    byte[] compressedChunk = new byte[1024];
                    int compressedDataLength = deflater.deflate(compressedChunk);

                    dos.writeInt(compressedDataLength);
                    dos.write(compressedChunk, 0, compressedDataLength);

                    deflater.end();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}