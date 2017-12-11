package jarvis.LCSMethod;

import jarvis.LCSMethod.service.SentenceManager;
import jarvis.LCSMethod.service.WordManager;
import jarvis.interfaces.CheckingSentenceForEqualse;

public class LCSMethod implements CheckingSentenceForEqualse {

    public boolean isEquals(String first, String second, int sureValue) {
        SentenceManager sm = new SentenceManager(first,second);
        return sm.getRating()>sureValue;
    }
    public boolean wordsIsEquals(String first, String second, int sureValue){
        WordManager wordManager = new WordManager();
        return wordManager.getRating(first,second)>sureValue;
    }
}
