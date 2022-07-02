package com.company.model;

import java.io.File;

public class ProtectedVersion {
    private String h0;
    private File protectedFile;

    public ProtectedVersion() {
    }

    public ProtectedVersion(String h0, File protectedFile) {
        this.h0 = h0;
        this.protectedFile = protectedFile;
    }

    public String getH0() {
        return h0;
    }

    public void setH0(String h0) {
        this.h0 = h0;
    }

    public File getProtectedFile() {
        return protectedFile;
    }

    public void setProtectedFile(File protectedFile) {
        this.protectedFile = protectedFile;
    }

    @Override
    public String toString() {
        return "ProtectedVersion{" +
                "h0='" + h0 + '\'' +
                ", protectedFile=" + protectedFile +
                '}';
    }
}
