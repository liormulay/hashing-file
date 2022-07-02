package com.company;

import java.io.File;

import static com.company.Utils.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Stargo\\Downloads\\zero.bin");
        Encoder encoder = new Encoder();
        ProtectedVersion protectedVersion = encoder.encodeFile(file);

        Decoder decoder = new Decoder();
        decoder.decodeFile(protectedVersion);

    }

}
