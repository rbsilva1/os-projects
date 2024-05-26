import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final int NUM_THREADS = 2;

    public static void main(String[] args) throws Exception {
        List<String> phrases = new ArrayList<>();
        File file = new File("src/filename.txt");
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            phrases.add(data);
        }

        reader.close();

        int batchSize = phrases.size() / NUM_THREADS;
        int startIndex = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            int endIndex = Math.min(startIndex + batchSize, phrases.size());
            new CountWords(phrases.subList(startIndex, endIndex), i).start();
            startIndex = endIndex;
        }
    }
}
