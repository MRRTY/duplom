package jarvis.dictionaryMethod.service;

import com.sun.xml.internal.ws.api.model.MEP;
import jarvis.dictionaryMethod.entity.*;
import jarvis.dictionaryMethod.exception.IncorrectFileException;
import jarvis.dictionaryMethod.exception.IncorrectFilePathException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class Dictionary {
    private Forest forest;
    public Dictionary(File jsonFile) {
        this.forest = fileToForest(jsonFile);
    }

    public void addMeanings(File jsonFile){
        if(forest != null){
            forest.getTreeSet().addAll(fileToForest(jsonFile).getTreeSet());
        }else {
            forest = fileToForest(jsonFile);
        }
        System.out.println(forest);

    }

    public Set<Meaning> getAllSynonyms(Word word){
        Set<Meaning> res = new HashSet<>();
        forest.getTreeSet().forEach(set->{

            if(set != null && set.getNet().contains(word))
                res.addAll(set.getNet());
        });
        return res;
    }

    public static Set<Meaning> intersectionOfSets(Set<Meaning> set1, Set<Meaning> set2){
        return set1.stream().filter(set2::contains).collect(Collectors.toSet());
    }

    public Set<Meaning> getAllMeanings(){
        Set<Meaning> res = new HashSet<>();
        forest.getTreeSet().forEach(tree -> {
            if (tree!=null && tree.getNet() != null)
                res.addAll(tree.getNet());
        });
        return res;
    }

    private Forest fileToForest(File jsonFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            forest = objectMapper.readValue(jsonFile,Forest.class);
            return forest;
        } catch (IOException e) {
            throw new IncorrectFileException();
        }
    }

    public Forest getForest() {
        return forest;
    }

    public void setForest(Forest forest) {
        this.forest = forest;
    }
}
