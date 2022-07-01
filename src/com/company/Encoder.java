package com.company;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.Main.sha;

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
            lastBlockSize = KB_LENGTH;
        }
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
            encodedBlocks.add(0, block);
        }
        return null;
    }

    private byte[] createHashing(Block block) {
        byte[] originalBytes = mergeArrays(block.getDataBytes(), block.getHashValue());
        byte[] encodedHash=sha(originalBytes);
        System.out.println("encodedHash.length "+encodedHash.length);
        return null;
    }

    private byte[] mergeArrays(@NotNull byte[] first, @Nullable byte[] second) {
        if (second == null) {
            return first;
        }
        int fal = first.length;        //determines length of firstArray
        int sal = second.length;   //determines length of secondArray
        byte[] result = new byte[fal + sal];
        System.arraycopy(first, 0, result, 0, fal);
        System.arraycopy(second, 0, result, fal, sal);
        return result;
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
