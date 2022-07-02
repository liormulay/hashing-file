package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Encoder.KB_LENGTH;
import static com.company.Utils.createHashing;
import static com.company.Utils.readFileToBytes;

public class Decoder {
    public static int SHA_LENGTH = 32;

    public static int ENCODED_BLOCK_LENGTH = KB_LENGTH + SHA_LENGTH;

    public File decodeFile(ProtectedVersion protectedVersion) throws MismatchException {
        String h0Hex = protectedVersion.getH0();
        byte[] h0Bytes = hexStringToByteArray(h0Hex);
        List<Block> blocks = parseFileToBlocks(protectedVersion.getProtectedFile());
        Block firstBlock = blocks.get(0);
        byte[] dataBytes = extractData(h0Bytes, firstBlock);
        return null;
    }

    private byte[] extractData(byte[] hash, Block block) throws MismatchException {
        byte[] hashBlock = createHashing(block);
        if (!Arrays.equals(hash, hashBlock)) {
            throw new MismatchException("Hashes are mismatch");
        }
        return block.getDataBytes();
    }

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
