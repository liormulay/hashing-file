package com.company;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static byte[] mergeArrays(@NotNull byte[] first, @Nullable byte[] second) {
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
}
