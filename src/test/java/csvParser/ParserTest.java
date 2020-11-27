package csvParser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parseLine()
    {
        List<String> expected=(Arrays.asList("10", "AU", "", "Au\"stralia"));
        List<String> actual =Parser.parseLine("10,AU,,Au\"\"stralia\n",',','\"');
        assertEquals(expected, actual);


        List<String> expected2=(Arrays.asList("14", "AU", "Aus/,tralia"));
        List<String> actual2 =Parser.parseLine("\"14\",\"AU\",\"Aus/,tra/*saaaaa/aaa*aas*/lia\"",',','\"');
        assertEquals(expected2, actual2);

        List<String> expected3=(Arrays.asList("14 \"AU \"Aus,tralia"));
        List<String> actual3 =Parser.parseLine("\"14\" \"AU\" \"Aus,tralia\"",',','\"');
        assertEquals(expected3, actual3);
    }

}