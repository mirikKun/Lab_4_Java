package csvParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Property ff= Input.newIn();

        Asker asker=new Asker(System.in, System.out);
        String numberOfThreads = asker.ask("Введите количество потоков: ");
        work(ff,numberOfThreads);
    }
    public static void work(Property ff, String numberOfThreads) throws IOException {
        File resultFile = new File("result.txt");
        String absoluteResultPath = resultFile.getAbsolutePath();
        Files.write(Paths.get(absoluteResultPath), new ArrayList<>(), StandardCharsets.UTF_8);
        File file = new File("src/main/resources/"+ff.pathTo);
        String absolutePath = file.getAbsolutePath();

       // ff.pathTo = "src/main/resources/test";
        List<String> allLines = DirectoryToList.listAllFiles(ff.pathTo);
        MnogoPotok concurrentParser = new MnogoPotok();
        concurrentParser.process(Integer.parseInt(numberOfThreads), allLines,ff.plus, ff.separator);
    }
}
