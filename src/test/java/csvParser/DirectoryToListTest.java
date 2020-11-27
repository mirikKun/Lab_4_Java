package csvParser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryToListTest {

    @Test
    void listAllFiles() {
       String expected="10,AU,,A/us90";
        List<String> actual = DirectoryToList.listAllFiles("src/main/resources/test");
        assertEquals(expected, actual.get(0));
    }
}