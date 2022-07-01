package com.company;

import java.io.File;

public class ProtectedVersion {
    private byte[] h0;
    private File protectedFile;

    public byte[] getH0() {
        return h0;
    }

    public void setH0(byte[] h0) {
        this.h0 = h0;
    }

    public File getProtectedFile() {
        return protectedFile;
    }

    public void setProtectedFile(File protectedFile) {
        this.protectedFile = protectedFile;
    }
}
