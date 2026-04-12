import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    @DisplayName("Create and load sample level")
    public void testCreateAndLoad() {
        loader.createSampleLevel();

        EndgameLoader.Level level = loader.loadLevel("level1.txt");

        assertNotNull(level);
        assertEquals("One Move Kill", level.name);
        assertEquals(Constants.BLACK, level.firstPlayer);
        assertEquals(1, level.targetMoves);
        assertEquals(15, level.board.length);
    }
    @Test
    @DisplayName("Load non-existent returns null")
    public void testLoadNonExistent() {
        assertNull(loader.loadLevel("none.txt"));
    }

    @Test
    @DisplayName("List levels")
    public void testListLevels() {
        loader.createSampleLevel();

        List<String> levels = loader.listLevels();

        assertEquals(1, levels.size());
        assertTrue(levels.contains("level1.txt"));
    }
}
