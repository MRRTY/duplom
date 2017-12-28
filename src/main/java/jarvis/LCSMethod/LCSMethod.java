package jarvis.LCSMethod;

import jarvis.LCSMethod.service.SentenceManager;
import jarvis.LCSMethod.service.WordManager;
import jarvis.interfaces.CheckingSentenceForEqualse;

public class LCSMethod implements CheckingSentenceForEqualse {

    public double getResult(String first, String second) {
        SentenceManager sm = new SentenceManager(first,second);
        return sm.getRating();
    }
    public boolean wordsIsEquals(String first, String second, int sureValue){
        WordManager wordManager = new WordManager();
        return wordManager.getRating(first,second)>sureValue;
    }

    public int getRatingForTwoWords(String s1, String s2) {
        WordManager wordManager = new WordManager();
        return wordManager.getRating(s1,s2);
    }
}
