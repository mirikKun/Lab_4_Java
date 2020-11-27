package csvParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void propertys(){
        Property prop = new Property();
        assertEquals(prop.separator,',');
        assertEquals(prop.plus,"+");
        assertEquals(prop.pathTo,"src/main/resources/directory1");


        Property prop1 = new Property("+");
        assertEquals(prop1.separator,',');
        assertEquals(prop1.plus,"+");
        assertEquals(prop1.pathTo,"src/main/resources/directory1");

        Property prop2 = new Property("+","src/main/resources/directory1");
        assertEquals(prop2.separator,',');
        assertEquals(prop2.plus,"+");
        assertEquals(prop2.pathTo,"src/main/resources/directory1");

        Property prop3 = new Property("+","src/main/resources/directory1",',');
        assertEquals(prop3.separator,',');
        assertEquals(prop3.plus,"+");
        assertEquals(prop3.pathTo,"src/main/resources/directory1");
    }
}