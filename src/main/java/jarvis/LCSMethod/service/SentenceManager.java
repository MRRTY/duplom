package jarvis.LCSMethod.service;

import jarvis.LCSMethod.entity.Sentence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SentenceManager {
    private Sentence first;
    private Sentence second;

    public SentenceManager(String first, String second) {
        this.first = createSentence(first);
        this.second = createSentence(second);
    }

    public Sentence createSentence(String sentence){
        List<String> words = Arrays.asList(sentence.split(" "));

        words = words.stream().map(word->word.replaceAll("[^A-Za-z0-9]+","")).collect(Collectors.toList());


        words = words.stream().filter(word -> word.length()>2).collect(Collectors.toList());

        return new Sentence(words);
    }
    public int getRating(){
        int res = 0;
        int min = 0;
        min = first.getWords().size()<=second.getWords().size() ? first.getWords().size() : second.getWords().size();


        for (String firstWord: first.getWords()){
            for(String secondWord: second.getWords()){
                if(method(firstWord,secondWord)){
                    res+= 100/min;
                    break;
                }
            }
        }

        return res;
    }

    private boolean method(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return false;
        }

        if (a.equals(b)) {
            return true;
        }

        int[][] matrix = new int[a.length()][];

        int maxLength = 0;
        int maxI = 0;

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[b.length()];
            for (int j = 0; j < matrix[i].length; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i != 0 && j != 0) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = 1;
                    }
                    if (matrix[i][j] > maxLength) {
                        maxLength = matrix[i][j];
                        maxI = i;
                    }
                }
            }
        }
        String min = a.length()>b.length() ? b : a;
        return (a.substring(maxI - maxLength + 1, maxI + 1).length()>min.length()/2);
    }
}
