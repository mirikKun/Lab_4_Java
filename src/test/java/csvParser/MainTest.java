package csvParser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void work() throws FileNotFoundException {
        String expected="2+2+0+6";
        Property propety = new Property("+", "src/main/resources/test", ',');
        Main.work(propety,"8");
        File file = new File("result.txt");
        Scanner scan= new Scanner(file);
        assertEquals(expected, scan.nextLine());

    }
}