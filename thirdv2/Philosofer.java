package thirdv2;

public class Philosofer extends Thread {
  private int idFilosofo;
  private Table table;

  public Philosofer(int idFilosofo, Table table) {
    this.idFilosofo = idFilosofo;
    this.table = table;
  }

  public void run() {
    try {
      while (true) {
        table.think(idFilosofo);
        table.getForks(idFilosofo);
        table.eat(idFilosofo);
        table.releaseForks(idFilosofo);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
