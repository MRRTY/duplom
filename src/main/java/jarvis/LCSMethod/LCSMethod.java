package jarvis.LCSMethod;

import jarvis.LCSMethod.service.SentenceManager;
import jarvis.interfaces.CheckingSentenceForEqualse;

public class LCSMethod implements CheckingSentenceForEqualse {

    public boolean isEquals(String first, String second, int sureValue) {
        SentenceManager sm = new SentenceManager(first,second);
        return sm.getRating()>sureValue;
    }
}
