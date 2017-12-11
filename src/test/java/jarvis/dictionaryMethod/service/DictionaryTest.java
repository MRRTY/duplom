package jarvis.dictionaryMethod.service;

import jarvis.dictionaryMethod.entity.Meaning;
import jarvis.dictionaryMethod.entity.Word;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class DictionaryTest {


    @Test
    @Ignore
    public void createDictionary() throws IOException {

        Dictionary dictionary = new Dictionary(new File("D:/test/db.json"));
        dictionary.getForest().getTreeSet().remove(null);
        System.out.println(dictionary.getAllMeanings().size());
        dictionary.getForest().getTreeSet().forEach(tree -> {
            tree.getNet().remove(null);
            Set<Meaning> toDelete = new HashSet<>();

            tree.getNet().forEach(meaning -> {
                if(meaning.getValue().length()<=2 || meaning.getValue().charAt(0)=="-".charAt(0) || meaning.getValue().charAt(meaning.getSize()-1)=="-".charAt(0)) {
                    toDelete.add(meaning);
                }

            });

            if(toDelete.size()>0) {

                toDelete.forEach(meaning -> {
                    System.out.println(tree.getNet().remove(meaning));

                });

                System.out.println(toDelete);
            }


        });

        System.out.println(dictionary.getAllMeanings().size());

        List<Meaning> res = new ArrayList<>(dictionary.getAllMeanings());
        res.sort(Comparator.comparingInt(o -> o.getValue().length()));

        System.out.println(res);
    }

    @Test
    public void loadDictionary() throws IOException {
        Dictionary dictionary = new Dictionary(new File("D:/test/db.json"));
        System.out.println(dictionary.getForest().getTreeSet().size());
        Set<Meaning> set1 = dictionary.getAllSynonyms(new Word("великий"));
        Set<Meaning> set2 = dictionary.getAllSynonyms(new Word("здоровий"));
        System.out.println(set1.size());
        System.out.println(set2.size());
        System.out.println(Dictionary.intersectionOfSets(set1,set2).size());

    }
    @Test
    public void check() throws IOException {
        Dictionary dictionary = new Dictionary(new File("D:/test/db.json"));
        dictionary.checkForEquals("Великий ліс", "Здорове дерево");

    }

    @Test
    public void findRoot() throws IOException {
        Dictionary dictionary = new Dictionary(new File("D:/test/db.json"));
        System.out.println(dictionary.findRoot("здорове"));

    }

}