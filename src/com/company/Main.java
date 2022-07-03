package com.company;

import com.company.model.ProtectedVersion;

import java.io.File;
import java.util.Objects;

import static com.company.Utils.*;

public class Main {

    public static final String ORIGINAL_FILE_PATH = "C:\\Users\\Stargo\\Downloads\\zero.bin";
    public static final String ENCODED_FILE_PATH = "C:\\Users\\Stargo\\Downloads\\zeroEncoded.bin";
    public static final String DECODED_FILE_PATH = "C:\\Users\\Stargo\\Downloads\\zeroDecoded.bin";

    public static void main(String[] args) {
        File file = new File(ORIGINAL_FILE_PATH);
        Encoder encoder = new Encoder();
        ProtectedVersion protectedVersion = encoder.encodeFile(file);

        Decoder decoder = new Decoder();
        try {
            File decodedFile = decoder.decodeFile(protectedVersion);
            byte[] fileBytes = readFileToBytes(decodedFile);
            byte[] shaFile = sha(fileBytes);
            String hashFile = encodeHexString(Objects.requireNonNull(shaFile));
            System.out.println("Decoded file hash: " + hashFile);
        } catch (MismatchException e) {
            System.out.println(e.getMessage());
        }

    }

}
