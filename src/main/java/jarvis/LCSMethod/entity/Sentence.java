package jarvis.LCSMethod.entity;

import java.util.List;

public class Sentence {
    private List<String> words;

    public Sentence() {
    }

    public Sentence(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
