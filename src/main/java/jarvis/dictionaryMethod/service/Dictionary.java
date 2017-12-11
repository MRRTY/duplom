package jarvis.dictionaryMethod.service;

import com.sun.xml.internal.ws.api.model.MEP;
import jarvis.LCSMethod.LCSMethod;
import jarvis.dictionaryMethod.entity.*;
import jarvis.dictionaryMethod.exception.IncorrectFileException;
import jarvis.dictionaryMethod.exception.IncorrectFilePathException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.*;
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

    public boolean checkForEquals(String first, String second){
        String[] firstArray = first.split(" ");
        String[] secondArray = second.split(" ");
        int resMatrix[][] = new int[firstArray.length][secondArray.length];
        for(int i = 0; i < firstArray.length; i++){
            for(int j = 0; j < secondArray.length; j++){
                resMatrix[i][j] = getRating(firstArray[i],secondArray[j]);
            }
        }
        for(int i = 0; i<resMatrix.length; i++){
            System.out.println(Arrays.toString(resMatrix[i]));
        }
        int res = getMaxMatch(resMatrix);
        return true;

    }

    private int getMaxMatch(int[][] resMatrix) {
        int[] res = new int[resMatrix.length];
        for(int i = 0; i <res.length; i++){
            int sum = resMatrix[i][0];
            

        }
    }

    private int getRating(String s1, String s2) {
        Set<Meaning> set1 = getAllSynonyms(new Word(s1));
        if(set1.isEmpty()){
            set1 = findRoot(s1);
        }
        Set<Meaning> set2 = getAllSynonyms(new Word(s2));
        if(set2.isEmpty()){
            set2 = findRoot(s2);
        }
        System.out.println(s1+" "+set1);
        System.out.println(s2+" "+set2);
        int s1Size = set1.size();
        int s2Size = set2.size();

        int cross = Dictionary.intersectionOfSets(set1,set2).size();
        return Math.min(s1Size,s2Size) != 0? cross*100/ Math.min(s1Size,s2Size) : 0;
    }

    public Set<Meaning> findRoot(String s1) {
        Set<Meaning> res = new HashSet<>();
        Set<Meaning> all = getAllMeanings();
        LCSMethod method = new LCSMethod();
        for (Meaning meaning: all){
            if(method.wordsIsEquals(s1,meaning.getValue() ,80)){
                res.addAll(getAllSynonyms(meaning));
            }
        }
        return res;
    }

    public Set<Meaning> getAllSynonyms(Meaning meaning){
        Set<Meaning> res = new HashSet<>();
        forest.getTreeSet().forEach(set->{

            if(set != null && set.getNet().contains(meaning))
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
