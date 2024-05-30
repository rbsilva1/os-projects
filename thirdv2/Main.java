package thirdv2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Thread> philosophers = new ArrayList<Thread>();

        Table table = new Table();

        for (int i = 0; i < 5; i++) {
            Thread philosopher = new Philosofer(i, table);
            philosophers.add(philosopher);
            philosopher.start();
        }

        for (Thread philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
