import java.util.ArrayList;
import java.util.List;

public class Recurrency {
  private List<Word> words = new ArrayList<>();

  public Recurrency() {

  }

  public void addWord(String newWord) {
    // System.out.println(newWord);
    synchronized (this) {
      if (words.size() == 0) {
        words.add(new Word(newWord, 1));
        return;
      }

      for (Word word : words) {
        if (word.getWord().equals(newWord)) {
          word.count++;
          return;
        }
      }

      words.add(new Word(newWord, 1));
    }
  }

  public void getWords(int numWords) {
    words.sort((a, b) -> b.getCount() - a.getCount());

    for (int i = 0; i < numWords; i++) {
      System.out.println(words.get(i).getWord() + " " + words.get(i).getCount());
    }
  }
}
