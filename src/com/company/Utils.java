package com.company;

import com.company.model.Block;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    @Nullable
    public static byte[] mergeArrays(@Nullable byte[] first, @Nullable byte[] second) {
        if (second == null) {
            return first;
        }
        if (first == null) {
            return second;
        }
        int fal = first.length;        //determines length of firstArray
        int sal = second.length;   //determines length of secondArray
        byte[] result = new byte[fal + sal];
        System.arraycopy(first, 0, result, 0, fal);
        System.arraycopy(second, 0, result, fal, sal);
        return result;
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

    public static String encodeHexString(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
        }
        return hexStringBuffer.toString();
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static byte[] readFileToBytes(File file) {
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            //read file into bytes[]
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static byte[] createHashing(Block block) {
        byte[] originalBytes = mergeArrays(block.getDataBytes(), block.getHashValue());
        return sha(originalBytes);
    }

    public static File createFile(byte[] bytes, String path) {
        File file = new File(path);

        // Try block to check for exceptions
        try {

            // Initialize a pointer in file
            // using OutputStream
            OutputStream os = new FileOutputStream(file);

            // Starting writing the bytes in it
            os.write(bytes);

            // Display message onconsole for successful
            // execution
            System.out.println("Successfully"
                    + " byte inserted");

            // Close the file connections
            os.close();
        }

        // Catch block to handle the exceptions
        catch (Exception e) {

            // Display exception on console
            System.out.println("Exception: " + e);
        }
        return file;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
