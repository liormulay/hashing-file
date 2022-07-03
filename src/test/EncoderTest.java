package test;

import com.company.Encoder;
import com.company.model.ProtectedVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.company.Main.ORIGINAL_FILE_PATH;
import static com.company.Utils.*;

class EncoderTest {

    private final Encoder encoder = new Encoder();

    private static final String EXCEPTED_H0 = "e6e19106304b9665184e957d5dbf58ad36c55b52bf8d993dae4340c96bd8e81f";

    private static final String EXCEPTED_ENCODED_FILE_HASH = "3857d5a53a726b96fe0de7f1b7bd0239c56aae4a5f9b9a75428cff6f850aeb56";


    @Test
    void validateEncodeFile() {
        File file = new File(ORIGINAL_FILE_PATH);
        ProtectedVersion protectedVersion = encoder.encodeFile(file);
        String h0 = protectedVersion.getH0();
        Assertions.assertEquals(h0, EXCEPTED_H0);
        File encodedFile = protectedVersion.getProtectedFile();
        byte[] encodedFileBytes = readFileToBytes(encodedFile);
        byte[] shaFile = sha(encodedFileBytes);
        Assertions.assertNotNull(shaFile);
        String hashEncodedFile = encodeHexString(shaFile);
        Assertions.assertEquals(hashEncodedFile,EXCEPTED_ENCODED_FILE_HASH);
    }
}