package csvParser;

import java.util.Objects;

public class Property {
    char separator;
    String plus;
    String pathTo;

    Property()
    {
        separator = ',';
        plus = "+";
        pathTo = "src/main/resources/directory1";
    }
    Property(String plas)
    {
        separator = ',';
        plus = plas;
        pathTo = "src/main/resources/directory1";
    }
    Property(String plas, String to)
    {
        separator = ',';
        plus = plas;
        pathTo = to;
    }

    Property(String plas, String to, char separ)
    {
        separator = separ;
        plus = plas;
        pathTo = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property propety = (Property) o;
        return separator == propety.separator &&
                plus.equals(propety.plus) &&
                pathTo.equals(propety.pathTo) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(separator, plus, pathTo);
    }
}
