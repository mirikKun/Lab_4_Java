package csvParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MnogoPotok {
    public void process(int numberOfThreads, List<String> lines, String plus, char separator, String absolutePath) {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        Formatter formatter = new Formatter();
        System.out.println("Время старта: " + LocalDateTime.now());
        LocalDateTime start = LocalDateTime.now();
        for (String line : lines) {
            service.execute(new ToThreats(line, plus, separator, absolutePath, formatter));
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
    }

    public static class ToThreats implements Runnable {

        String plus;
        String line;
        char separator;
        String absolutePath;
        Formatter formatter;

        public ToThreats(String line, String plus, char separator, String absolutePath, Formatter formatter) {
            this.line = line;
            this.separator = separator;
            this.absolutePath = absolutePath;
            this.formatter = formatter;
            this.plus = plus;
        }

        @Override
        public void run() {
            try {
                FileWriter fstream = new FileWriter("result.txt", true);
                BufferedWriter result = new BufferedWriter(fstream);
                result.write(formatter.format(Parser.parseLine(line, ',', '"'), plus));
                result.close();
            } catch (Exception e) {
                System.err.println("Error while writing to file: " +
                        e.getMessage());
            }
        }
    }
}
