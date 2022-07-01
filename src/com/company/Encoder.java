package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Encoder {
    public ProtectedVersion encodeFile(File file) {
        byte[] bytes = readFileToBytes(file);
        System.out.println("File file" + bytes.length);
        return null;
    }

    private byte[] readFileToBytes(File file)  {
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
