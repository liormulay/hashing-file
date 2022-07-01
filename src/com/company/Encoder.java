package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encoder {
    public static final int KB_LENGTH = 1024;

    public static final int SHA256_LENGTH = 32;

    public ProtectedVersion encodeFile(File file) {
        byte[] bytes = readFileToBytes(file);
        decodeBytes(bytes);
        return null;
    }

    private ProtectedBytes decodeBytes(byte[] originalBytes) {
        int numOfIterations = originalBytes.length / KB_LENGTH;
        int lastBlockSize = originalBytes.length - numOfIterations * KB_LENGTH;

        if (numOfIterations * KB_LENGTH == originalBytes.length) {
            numOfIterations--;
            lastBlockSize = KB_LENGTH;
        }
        System.out.println("lastBlockSize " + lastBlockSize);
        List<Block> encodedBlocks = new ArrayList<>();
        byte[] lastBytes = Arrays.copyOfRange(originalBytes, originalBytes.length - lastBlockSize, originalBytes.length);
        Block lastBlock = new Block(lastBytes);
        encodedBlocks.add(lastBlock);
        return null;
    }

    private byte[] readFileToBytes(File file) {
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            //read file into bytes[]
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
