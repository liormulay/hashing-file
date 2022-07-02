package com.company.model;

import java.util.List;

public class ProtectedBytes {
    private byte[] h0;
    private List<Block> blocks;

    public byte[] getH0() {
        return h0;
    }

    public void setH0(byte[] h0) {
        this.h0 = h0;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
