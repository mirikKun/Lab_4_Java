package csvParser;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {
    private Formatter formatter;
    @BeforeEach
    public void setUp() {
        formatter = new Formatter();
    }

    @org.junit.jupiter.api.Test
    void format() {
        String expected="2+2+0+9\n";

        String actual =formatter.format(Arrays.asList("10", "AU", "", "Australia"),"+");
        assertEquals(expected, actual);
    }
}