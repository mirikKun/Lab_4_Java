package csvParser;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Property ff= Input.newIn();

        Asker asker=new Asker(System.in, System.out);
        String numberOfThreads = asker.ask("Введите количество потоков: ");
        work(ff,numberOfThreads);
    }
    public static void work(Property ff, String numberOfThreads)  {
        File file = new File("src/main/resources/"+ff.pathTo);
        String absolutePath = file.getAbsolutePath();

       // ff.pathTo = "src/main/resources/test";
        List<String> allLines = DirectoryToList.listAllFiles(ff.pathTo);
        MnogoPotok concurrentParser = new MnogoPotok();
        concurrentParser.process(Integer.parseInt(numberOfThreads), allLines,ff.plus, ff.separator,  absolutePath);
    }
}
