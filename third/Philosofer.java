package third;

public class Philosofer extends Thread{
    int id;
    int id_garfo_esquerda = this.id;
    int id_garfo_direita = (this.id + 1) % 5;
    Table mesa;

    public Philosofer(int id, Table mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while (true) {
            mesa.pensar(id);
            mesa.comer(id);
        }
    }
}
