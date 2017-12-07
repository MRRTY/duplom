package jarvis.dictionaryMethod.entity;

public class Phrase extends  Meaning {
    String value;

    public Phrase() {
    }

    @Override
    public int getSize() {
        return 0;
    }

    public Phrase(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "value='" + value + '\'' +
                '}';
    }
}
