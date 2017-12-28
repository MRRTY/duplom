package jarvis.dictionaryMethod;

import jarvis.dictionaryMethod.service.Dictionary;
import jarvis.interfaces.CheckingSentenceForEqualse;

import java.io.File;

public class DictionaryMethod implements CheckingSentenceForEqualse {

    @Override
    public double getResult(String first, String second) {
        Dictionary dictionary = new Dictionary(new File("D:/test/db.json"));
        return dictionary.checkForEquals(first,second)/100;
    }
}
