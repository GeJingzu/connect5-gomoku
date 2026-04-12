import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class EndgameLoaderTest {

    private static final String TEST_PATH = "test_levels/";
    private EndgameLoader loader;

    @BeforeEach
    void setUp() {
        deleteDir(new File(TEST_PATH));
        loader = new EndgameLoader(TEST_PATH);
    }

    @AfterEach
    void tearDown() {
        deleteDir(new File(TEST_PATH));
    }

    void deleteDir(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) f.delete();
            }
            dir.delete();
        }
    }
}
