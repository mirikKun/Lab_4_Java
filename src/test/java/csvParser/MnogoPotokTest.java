package csvParser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MnogoPotokTest {

    @Test
    void process() throws IOException {
        String expected="2+2+0+6";
        List<String> line=(Arrays.asList("10", "AU", "", "Au\"stralia"));
        MnogoPotok concurrentParser = new MnogoPotok();
        concurrentParser.process(8, line, "+", ',');
        File file = new File("result.txt");
        Scanner scan= new Scanner(file);
        assertEquals(expected, scan.nextLine());
    }
}