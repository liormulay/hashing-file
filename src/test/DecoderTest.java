package test;

import com.company.Decoder;
import com.company.MismatchException;
import com.company.model.ProtectedVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static com.company.Main.ENCODED_FILE_PATH;
import static com.company.Utils.*;
import static org.junit.jupiter.api.Assertions.fail;

class DecoderTest {

    private final Decoder decoder = new Decoder();

    @Test
    void decodeFile() {
        String h0 = "e6e19106304b9665184e957d5dbf58ad36c55b52bf8d993dae4340c96bd8e81f";
        File encodedFile = new File(ENCODED_FILE_PATH);
        ProtectedVersion protectedVersion = new ProtectedVersion(h0, encodedFile);
        try {
            File decodedFile = decoder.decodeFile(protectedVersion);
            byte[] fileBytes = readFileToBytes(decodedFile);
            byte[] shaFile = sha(fileBytes);
            String hashFile = encodeHexString(Objects.requireNonNull(shaFile));
            Assertions.assertEquals(hashFile,"95b532cc4381affdff0d956e12520a04129ed49d37e154228368fe5621f0b9a2");
        } catch (MismatchException e) {
            fail("MismatchException, message: " + e.getMessage());
        }
    }

    @Test
    void hexStringToByteArray() {
    }
}