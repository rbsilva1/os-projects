import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static final int NUM_THREADS = 2;
    public static final int NUM_WORDS = 3;

    public static void main(String[] args) throws Exception {
        List<String> phrases = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Recurrency recurrency = new Recurrency();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File("first-question/src/teste.txt"))))) {

            String phrase;
            while ((phrase = br.readLine()) != null) {
                phrases.add(phrase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int batchSize = phrases.size() / NUM_THREADS;
        int startIndex = 0;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            int endIndex = Math.min(startIndex + batchSize, phrases.size());
            if (endIndex == phrases.size() - 1 && i == NUM_THREADS - 1) {
                endIndex = phrases.size();
            }
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

        recurrency.getWords(3);
        // recurrency.getWords(NUM_WORDS);

        long endTime = System.currentTimeMillis();

        System.out.println("Number of threads: " + NUM_THREADS);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
