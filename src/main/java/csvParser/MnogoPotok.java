package csvParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

public class MnogoPotok {
    public void process(int numberOfThreads, List<String> lines, String plus, char separator) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        Formatter formatter = new Formatter();
        System.out.println("Время старта: " + LocalDateTime.now());
        LocalDateTime start = LocalDateTime.now();
        int counter = 1;
        for (String line : lines) {
            service.execute(new ToThreats(counter++, line, plus, separator, formatter));
        }
        service.shutdown();
        try
        {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("\nВремя окончания: " + LocalDateTime.now()+'\n');
        System.out.println("Длительность в ms: ");
        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
        System.out.println('\n');
        File file = new File("result.txt");
        List<String> sortedLines = Files.lines(file.toPath()).sorted(Comparator.comparing(line -> Integer.valueOf(line.split("@_@")[0]))).map(line -> line.split("@_@")[1]).collect(toList());
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), sortedLines, StandardCharsets.UTF_8);
    }

    public static class ToThreats implements Runnable {

        String plus;
        String line;
        char separator;
        Formatter formatter;
        int counter;

        public ToThreats(int counter, String line, String plus, char separator, Formatter formatter) {
            this.line = line;
            this.separator = separator;
            this.formatter = formatter;
            this.plus = plus;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                FileWriter fstream = new FileWriter("result.txt", true);
                BufferedWriter result = new BufferedWriter(fstream);
                result.write(counter+"@_@"+formatter.format(Parser.parseLine(line, ',', '"'), plus));
                result.close();
            } catch (Exception e) {
                System.err.println("Error while writing to file: " +
                        e.getMessage());
            }
        }
    }
}
