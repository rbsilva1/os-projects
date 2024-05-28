import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final int NUM_THREADS = 2;
    public static final int NUM_WORDS = 3;

    public static void main(String[] args) throws Exception {
        List<String> phrases = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        File file = new File("src/filename.txt");
        Recurrency recurrency = new Recurrency();
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            phrases.add(data);
        }

        reader.close();

        int batchSize = phrases.size() / NUM_THREADS;
        int startIndex = 0;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            int endIndex = Math.min(startIndex + batchSize, phrases.size());
            Thread thread = new CountWords(phrases.subList(startIndex, endIndex), recurrency);
            threads.add(thread);
            thread.start();
            startIndex = endIndex;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                throw new Error("Thread failed to join");
            }
        }

        recurrency.getWords(NUM_WORDS);

        long endTime = System.currentTimeMillis();

        System.out.println("Number of threads: " + NUM_THREADS);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
