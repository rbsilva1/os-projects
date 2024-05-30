package thirdv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
    List<Integer> garfos = new ArrayList<Integer>();
    int FILOSOFO_LIMITE = 5;

    public Table() {
        for (int i = 0; i < 5; i++) {
            garfos.add(1);
        }
    }

    public void think(int idFilosofo) {
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("Filósofo " + idFilosofo + " está pensando");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat(int idFilosofo) {
        try {
            System.out.println("\n\nFilósofo " + idFilosofo + " está [COMENDO]\n\n");
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getForks(int idFilosofo) throws InterruptedException {
        synchronized (this) {
            int idGarfoEsquerda = idFilosofo;
            int idGarfoDireita = (idFilosofo + 1) % 5;

            while (garfos.get(idGarfoEsquerda) != 1 || garfos.get(idGarfoDireita) != 1) {
                System.out.println("Filósofo " + idFilosofo + " está esperando os garfos");
                wait(); // Espera até que os garfos estejam disponíveis
            }

            // Garfos estão disponíveis, então marca como ocupados
            garfos.set(idGarfoEsquerda, 2);
            garfos.set(idGarfoDireita, 2);
        }
    }

    public void releaseForks(int idFilosofo) {
        synchronized (this) {
            int idGarfoEsquerda = idFilosofo;
            int idGarfoDireita = (idFilosofo + 1) % 5;

            // Libera os garfos e notifica os outros filósofos
            garfos.set(idGarfoEsquerda, 1);
            garfos.set(idGarfoDireita, 1);
            System.out.println("\n\nFilósofo " + idFilosofo + " [SOLTOU] os garfos\n\n");
            this.notifyAll(); // Notifica todos os filósofos que estão esperando
        }
    }
}
