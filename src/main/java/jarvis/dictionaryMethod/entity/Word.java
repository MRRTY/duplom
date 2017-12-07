package jarvis.dictionaryMethod.entity;

public class Word extends Meaning implements Comparable{
    private String value;

    public Word() {
    }

    @Override
    public int getSize() {
        return value.length();
    }

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return value != null ? value.equalsIgnoreCase(word.value) : word.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.toLowerCase().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        Word obj = (Word) o;
        return this.value.length()- obj.value.length();
    }
}
