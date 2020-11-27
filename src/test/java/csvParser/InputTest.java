package csvParser;

import org.junit.jupiter.api.Test;

import static csvParser.Input.getIn;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class InputTest {



    @Test
    void asd() {
        Asker asker = mock(Asker.class);
        when(asker.ask("Введите итоговый сепаратор(enter чтобы пропустить):")).thenReturn("+");
        when(asker.ask("Введите путь к директории:")).thenReturn("src/main/resources/directory1");
        when(asker.ask("Введите сепаратор:")).thenReturn(",");

        Property expectedProperty = new Property();
        Property actualProperty = getIn(asker);
        assertEquals(expectedProperty, actualProperty);

        when(asker.ask("Введите итоговый сепаратор(enter чтобы пропустить):")).thenReturn("");

        Property expectedProperty1 = new Property();
        Property actualProperty1 = getIn(asker);
        assertEquals(expectedProperty1, actualProperty1);
    }
}