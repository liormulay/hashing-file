package com.company;

import java.io.File;
import java.util.Objects;

import static com.company.Utils.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Stargo\\Downloads\\zero.bin");
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
