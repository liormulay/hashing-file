package com.company;

import java.io.File;

import static com.company.Utils.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Stargo\\Downloads\\zero.bin");
        Encoder encoder = new Encoder();
        ProtectedVersion protectedVersion = encoder.encodeFile(file);
        System.out.println("The h for this file is: " + protectedVersion.getH0());
        byte[] fileBytes = readFileToBytes(protectedVersion.getProtectedFile());
        byte[] shaFile = sha(fileBytes);
        String hexString = encodeHexString(shaFile);
        System.out.println("The hash of the encoded file is: " + hexString);

    }

}
