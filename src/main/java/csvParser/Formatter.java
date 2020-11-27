package csvParser;

import java.util.List;

public class Formatter {
    public  String format(List<String> Pline,String sep)
    {
        StringBuilder result = new StringBuilder();

            for (String ch : Pline){
                result.append(ch.length());
                if(!Pline.get(Pline.size()-1).equals(ch)) {
                    result.append(sep);
                }
            }
            result.append('\n');
        return  result.toString();
    }
}
