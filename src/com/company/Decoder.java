package com.company;

import com.company.model.Block;
import com.company.model.ProtectedVersion;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Encoder.KB_LENGTH;
import static com.company.Main.DECODED_FILE_PATH;
import static com.company.Utils.*;

public class Decoder {
    public static int SHA_LENGTH = 32;

    public static int ENCODED_BLOCK_LENGTH = KB_LENGTH + SHA_LENGTH;

    /**
     * This method get encoded file and hex string, validate it and decoded it to decoded file. <br>
     * If the validation is fail then it will throw a {@link MismatchException} <br>
     * First parse the file into blocks<br>
     * Then algorithm loop over all blocks from the begging merged the data and the hash value
     * to one bytes array hash those bytes and validate that it equal to the previous hash (in the first block it h0)
     * if yes then it extract the data bytes and contact them to array if not it throw a {@link MismatchException} <br>
     * When it finish ti build the decode array it build a decoded file from it
     *
     * @param protectedVersion contain decoded file and hex h0 string
     * @return decoded file
     * @throws MismatchException if validation is failed
     */
    public File decodeFile(ProtectedVersion protectedVersion) throws MismatchException {
        String h0Hex = protectedVersion.getH0();
        byte[] h0Bytes = hexStringToByteArray(h0Hex);
        List<Block> blocks = parseFileToBlocks(protectedVersion.getProtectedFile());
        Block firstBlock = blocks.get(0);
        byte[] dataBytes = validateAndExtractData(h0Bytes, firstBlock);
        for (int i = 1; i < blocks.size(); i++) {
            byte[] hashValue = blocks.get(i - 1).getHashValue();
            Block block = blocks.get(i);
            byte[] blockData = validateAndExtractData(hashValue, block);
            dataBytes = mergeArrays(dataBytes, blockData);
        }
        return createFile(dataBytes, DECODED_FILE_PATH);
    }

    /**
     * Create hash from the block and validate that it equal to the hash param,
     * if yes then it return the data of the block if not it will throw a {@link MismatchException}
     *
     * @param hash that suppose to be equal to the hash of the block
     * @param block validate is hashing equal to hash param and return his data
     * @return the data of block
     * @throws MismatchException if hash of block is not equal to the hash param
     */
    private byte[] validateAndExtractData(byte[] hash, Block block) throws MismatchException {
        byte[] hashBlock = createHashing(block);
        if (!Arrays.equals(hash, hashBlock)) {
            throw new MismatchException("Hashes are mismatch");
        }
        return block.getDataBytes();
    }

    /**
     * Get a file and parse it to blocks <br>
     * First read file to bytes, then loop over the bytes array from the beginning,
     * in each iteration (jumps of encoded block length) create a block that fist 1024 (KB_LENGTH) with be the data bytes
     * and the 32 (SHA_LENGTH) next bytes will be the sha bytes
     *
     * @param file to be parsed to blocks
     * @return list of {@link Block}
     */
    private List<Block> parseFileToBlocks(File file) {
        List<Block> blocks = new ArrayList<>();
        byte[] bytes = readFileToBytes(file);
        int i;
        for (i = 0; (i + ENCODED_BLOCK_LENGTH) < bytes.length; i += ENCODED_BLOCK_LENGTH) {
            byte[] dataBytes = Arrays.copyOfRange(bytes, i, i + KB_LENGTH);
            byte[] hashValue = Arrays.copyOfRange(bytes, i + KB_LENGTH, i + KB_LENGTH + SHA_LENGTH);
            Block block = new Block(dataBytes, hashValue);
            blocks.add(block);
        }
        byte[] dataBytes = Arrays.copyOfRange(bytes, i, bytes.length);
        Block lastBlock = new Block(dataBytes);
        blocks.add(lastBlock);
        return blocks;
    }

}
