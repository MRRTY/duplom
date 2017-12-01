package jarvis.dictionaryMethod.service;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class DictionaryTest {
    @Test
    public void addMeanings() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.addMeanings(new File("D:/test/test.json"));

    }

    @Test
    public void addFewMeanings() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.addMeanings(new File("D:/test/test.json"));
        dictionary.addMeanings(new File("D:/test/test.json"));

    }

    @Test
    public void createDictionary() throws IOException {

    }

}