package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHasher {

    private static FileHasher instance;


    private FileHasher() {
    }

    public static FileHasher getInstance() {
        if (instance == null) {
            instance = new FileHasher();
        }
        return instance;
    }

    public String hashContent(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, bytesRead);
            }
            byte[] digest = messageDigest.digest();

            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
                // we need to apply the mask to avoid unexpected output when signed byte is promoted to the int
                // to promote byte to unsigned value we can apply the mask 0xFF with and operator
            }

            return hexString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
