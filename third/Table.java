package third;

import java.util.ArrayList;
import java.util.Random;;

public class Table {
    // O filosófo com ID = 0 irá pegar os garfos das casas 0 e 1
    // O filósofo com ID = 1 irá pegar os garfos das casas 1 e 2
    // O filósofo com ID = 2 irá pegar os garfos das casas 2 e 3
    // O filosofo com ID = 3 irá pegar os garfos das casas 3 e 4
    // O filósofo com ID = 4 irá pegar os garfos das casas 4 e 0

    // Matematicamente, as casas dos garfos podems er calculadas com:
    // para a esquerda -> id_garfo_esquerda = id_filósofo
    // para a diretia -> id_garfo_direita = id_filósofo + 1 SE ID_FILOSOFO = 4: id_garfo_direita = 0

    ArrayList<Integer> garfos = new ArrayList<Integer>();

    // 1 -> GARFO LIVRE
    // 0 -> GARFO OCUPADO

    int GARFO_LIMITE = 5;
    int FILOSOFO_LIMITE = 5;
    int id_garfo_esquerda;
    int id_garfo_direita;

    public Table() {
        for ( int i = 0; i < 5; i++ ) {
            garfos.add(1);
        }
    }

    public void comer(int id_filosofo) {
        System.out.println(">> Filósofo " + id_filosofo + " quer comer");
        try {
            synchronized(garfos) {
                id_garfo_esquerda = id_filosofo;
                id_garfo_direita = (id_filosofo + 1) % FILOSOFO_LIMITE;
                System.out.println(">> Filósofo " + id_filosofo + "está pégando os garfos: " + id_garfo_esquerda + " " + id_garfo_direita + " com valores: " + garfos.get(id_garfo_direita) + " " + garfos.get(id_garfo_esquerda));
                if (garfos.get(id_garfo_esquerda) == 1 && garfos.get(id_garfo_direita) == 1) {
                    System.out.println("\n\n>> Filósofo " + id_filosofo + " [ESTÁ COMENDO]\n\n");
                    garfos.set(id_garfo_esquerda, 0);
                    garfos.set(id_garfo_direita, 0);
                    Thread.sleep(new Random().nextInt(3000));
                    garfos.set(id_garfo_esquerda, 1);
                    garfos.set(id_garfo_direita, 1);
                    System.out.println(">> Filósofo " + id_filosofo + " soltou os garfos");
                    // garfos.get(id_garfo_direita).notify();
                    // garfos.get(id_garfo_esquerda).notify();
                }
            }
        } catch (Exception e) {}
    }

    public void pensar(int id_filosofo) {
        try {
            System.out.println(">> FIlósofo " + id_filosofo + " está pensando");
            Thread.sleep(new Random().nextInt(3000));
        } catch (Exception e) {}
    }
}
