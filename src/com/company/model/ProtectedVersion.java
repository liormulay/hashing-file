package com.company.model;

import java.io.File;

/**
 * This class represent the encoder response
 */
public class ProtectedVersion {
    /**
     * hex string of h0
     */
    private String h0;
    /**
     * The encoded file
     */
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
