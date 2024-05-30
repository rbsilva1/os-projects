package third;

public class Philosofer extends Thread {
  int idFilosofo;
  Table mesa;

  public Philosofer(int idFilosofo, Table mesa) {
    this.idFilosofo = idFilosofo;
    this.mesa = mesa;
  }

  public void run() {
    while (true) {
      mesa.pensar(idFilosofo);
      mesa.comer(idFilosofo);
    }
  }
}
