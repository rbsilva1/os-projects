import java.util.List;

public class CountWords extends Thread {
  private int id;
  private List<String> phrases;

  public CountWords(List<String> phrases, int id) {
    this.phrases = phrases;
    this.id = id;
  }

  public void run() {
    try {
      for (int i = 0; i < phrases.size(); i++) {
        int count = phrases.get(i).split("\\s").length;
        System.out.println("Thread " + id + " " + count);
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}