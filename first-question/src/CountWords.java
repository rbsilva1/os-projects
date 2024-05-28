import java.util.List;

public class CountWords extends Thread {
  private List<String> phrases;
  private Recurrency recurrency;

  public CountWords(List<String> phrases, Recurrency recurrency) {
    this.phrases = phrases;
    this.recurrency = recurrency;
  }

  public void run() {
    try {
      for (int i = 0; i < phrases.size(); i++) {
        for (String word : phrases.get(i).split("\\s")) {
          recurrency.addWord(word);
        }
        // Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}