package jarvis.dictionaryMethod.service;

import jarvis.dictionaryMethod.entity.Forest;
import jarvis.dictionaryMethod.entity.Phrase;
import jarvis.dictionaryMethod.entity.Tree;
import jarvis.dictionaryMethod.entity.Word;
import jarvis.dictionaryMethod.exception.IncorrectFileException;
import jarvis.dictionaryMethod.exception.IncorrectFilePathException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.File;
import java.io.IOException;


public class Dictionary {
    private Forest forest;
    public Dictionary() {}

    public void addMeanings(File jsonFile){
        if(forest != null){
            forest.getTreeSet().addAll(fileToForest(jsonFile).getTreeSet());
        }else {
            forest = fileToForest(jsonFile);
        }
        System.out.println(forest);

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



}
