package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.Utils.*;

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
    void encodeHexString_test() {
        byte[] bytes = new byte[]{0, 1, 10, 30};
        final String hexString = encodeHexString(bytes);
        Assertions.assertEquals(hexString, "00010a1e");
    }

    @Test
    void byteToHex_test() {
        String hexString = byteToHex((byte) 3);
        Assertions.assertEquals(hexString, "03");
        hexString = byteToHex((byte) 10);
        Assertions.assertEquals(hexString, "0a");
        hexString = byteToHex((byte) 123);
        Assertions.assertEquals(hexString, "7b");
        hexString = byteToHex((byte) 255);
        Assertions.assertEquals(hexString, "ff");
    }

}