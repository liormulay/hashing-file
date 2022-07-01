package com.company;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Stargo\\Downloads\\zero.bin");
        Encoder encoder = new Encoder();
        encoder.encodeFile(file);
    }

    public static byte[] sha(byte[] bytes) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (digest != null) {
            return digest.digest(bytes);
        }
        return null;
    }

}
