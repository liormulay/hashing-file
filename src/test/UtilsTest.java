package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.Utils.mergeArrays;

class UtilsTest {

    @Test
    void mergeArrays_test() {
        byte[] fist = {0, 1};
        byte[] second = {2, 3};
        byte[] merge = mergeArrays(fist, null);
        Assertions.assertArrayEquals(fist, merge);
        merge = mergeArrays(null, second);
        Assertions.assertArrayEquals(second, merge);
        merge = mergeArrays(fist, second);
        Assertions.assertArrayEquals(new byte[]{0, 1, 2, 3}, merge);
    }

    @Test
    void sha() {
    }

    @Test
    void encodeHexString() {
    }

    @Test
    void byteToHex() {
    }

    @Test
    void readFileToBytes() {
    }

    @Test
    void createHashing() {
    }

    @Test
    void createFile() {
    }
}