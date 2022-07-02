package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Utils.*;
import static com.company.Utils.encodeHexString;

public class Encoder {

    public static final int KB_LENGTH = 1024;

    public ProtectedVersion encodeFile(File file) {
        byte[] bytes = readFileToBytes(file);
        ProtectedBytes protectedBytes = encodeBytes(bytes);
        return protectedBytesToResponse(protectedBytes);
    }

    private ProtectedVersion protectedBytesToResponse(ProtectedBytes protectedBytes) {
        String hexH0 = encodeHexString(protectedBytes.getH0());
        byte[] encodedBytes = null;
        for (Block block : protectedBytes.getBlocks()) {
            encodedBytes = mergeArrays(encodedBytes, block.getDataBytes());
            encodedBytes = mergeArrays(encodedBytes, block.getHashValue());
        }
        File encodedFile = createFile(encodedBytes);
        return new ProtectedVersion(hexH0, encodedFile);
    }

    private ProtectedBytes encodeBytes(byte[] originalBytes) {
        ProtectedBytes protectedBytes = new ProtectedBytes();
        int numOfIterations = originalBytes.length / KB_LENGTH;
        int lastBlockSize = numOfIterations * KB_LENGTH == originalBytes.length
                ? KB_LENGTH
                : originalBytes.length - numOfIterations * KB_LENGTH;

        List<Block> encodedBlocks = new ArrayList<>();
        int lastBlockFromIndex = originalBytes.length - lastBlockSize;
        byte[] lastBytes = Arrays.copyOfRange(originalBytes, lastBlockFromIndex, originalBytes.length);
        Block lastBlock = new Block(lastBytes);
        encodedBlocks.add(lastBlock);
        for (int i = lastBlockFromIndex; i >= KB_LENGTH; i -= KB_LENGTH) {
            byte[] dataBytes = Arrays.copyOfRange(originalBytes, i - KB_LENGTH, i);
            Block prevBlock = encodedBlocks.get(0);
            byte[] hashValue = createHashing(prevBlock);
            Block block = new Block(dataBytes);
            block.setHashValue(hashValue);
            encodedBlocks.add(0, block);
        }
        Block firstBlock = encodedBlocks.get(0);
        byte[] h0Bytes = createHashing(firstBlock);
        protectedBytes.setH0(h0Bytes);
        protectedBytes.setBlocks(encodedBlocks);
        return protectedBytes;
    }



    private File createFile(byte[] bytes) {
        File file = new File("C:\\Users\\Stargo\\Downloads\\zeroEncoded.bin");

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
}
