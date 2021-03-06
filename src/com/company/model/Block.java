package com.company.model;

public class Block {
    /**
     * hold the original data
     */
    private byte[] dataBytes;
    /**
     * hold the result of hash method
     */
    private byte[] hashValue;

    public Block() {
    }

    public Block(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public Block(byte[] dataBytes, byte[] hashValue) {
        this.dataBytes = dataBytes;
        this.hashValue = hashValue;
    }

    public byte[] getDataBytes() {

        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public byte[] getHashValue() {
        return hashValue;
    }

    public void setHashValue(byte[] hashValue) {
        this.hashValue = hashValue;
    }
}
