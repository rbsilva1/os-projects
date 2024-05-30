package third;

import java.util.Random;

public class Table {
    Object[] garfos;
    int FILOSOFO_LIMITE = 5;

    public Table() {
        garfos = new Object[FILOSOFO_LIMITE];
        for (int i = 0; i < FILOSOFO_LIMITE; i++) {
            garfos[i] = new Object();
        }
    }

    public void comer(int id_filosofo) {
        int id_garfo_esquerda = id_filosofo;
        int id_garfo_direita = (id_filosofo + 1) % FILOSOFO_LIMITE;

        System.out.println(">> Filósofo " + id_filosofo + " quer comer");

        synchronized (garfos[id_garfo_esquerda]) {
            synchronized (garfos[id_garfo_direita]) {
                System.out.println("\n\n>> Filósofo " + id_filosofo + " [ESTÁ COMENDO]\n\n");
                try {
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                }

                System.out.println(">> Filósofo " + id_filosofo + " soltou os garfos");
            }
        }
    }

    public void pensar(int id_filosofo) {
        System.out.println(">> Filósofo " + id_filosofo + " está pensando");
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
        }
    }
}
