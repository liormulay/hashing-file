package com.company;

import com.company.model.Block;
import com.company.model.ProtectedBytes;
import com.company.model.ProtectedVersion;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Main.ENCODED_FILE_PATH;
import static com.company.Utils.*;
import static com.company.Utils.encodeHexString;

public class Encoder {

    public static final int KB_LENGTH = 1024;

    /**
     * This method get a file and encode it
     *
     * @param file the file to be encoded
     * @return encoded file and hex string of h0
     */
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
        File encodedFile = createFile(encodedBytes, ENCODED_FILE_PATH);
        return new ProtectedVersion(hexH0, encodedFile);
    }

    /**
     * This method get an array of bytes and encoded them <br>
     * the algorithm is going from the end of the array<br>
     * calculate the size of the last block build it and add it to the list <br><br>
     * Then it looping on the array from the prev block index and jump by block length <br>
     * In each iteration it build new block: <br>
     * the part of data of taken from the bytes array <br>
     * the part of hash is build by get the last block that inserted to the list,
     * concat his data and hash to one array the hash this array <br>
     * after the block is completed inset it at index 0 in the list <br><br>
     * When the list is completed it build the h0 by get the first block from the list and hash it
     *
     * @param originalBytes the bytes to be encoded
     * @return list of encoded blocks plus h0
     */
    private ProtectedBytes encodeBytes(byte[] originalBytes) {
        ProtectedBytes protectedBytes = new ProtectedBytes();
        int numOfCompletedBlocks = originalBytes.length / KB_LENGTH;
        //if numOfCompletedBlocks * KB_LENGTH = originalBytes.length
        //that's mean that the array's length is divided by the block size and the last block will be also in completed size (of data)
        int lastBlockSize = numOfCompletedBlocks * KB_LENGTH == originalBytes.length
                ? KB_LENGTH
                : originalBytes.length - numOfCompletedBlocks * KB_LENGTH;

        List<Block> encodedBlocks = new ArrayList<>();
        //the index in the bytes array that the last block start
        int lastBlockFromIndex = originalBytes.length - lastBlockSize;
        byte[] lastBytes = Arrays.copyOfRange(originalBytes, lastBlockFromIndex, originalBytes.length);
        Block lastBlock = new Block(lastBytes);
        encodedBlocks.add(lastBlock);
        for (int i = lastBlockFromIndex; i >= KB_LENGTH; i -= KB_LENGTH) {
            byte[] dataBytes = Arrays.copyOfRange(originalBytes, i - KB_LENGTH, i);
            Block prevBlock = encodedBlocks.get(0);
            byte[] hashValue = createHashing(prevBlock);
            Block block = new Block(dataBytes, hashValue);
            encodedBlocks.add(0, block);
        }
        Block firstBlock = encodedBlocks.get(0);
        byte[] h0Bytes = createHashing(firstBlock);
        protectedBytes.setH0(h0Bytes);
        protectedBytes.setBlocks(encodedBlocks);
        return protectedBytes;
    }

}
