package jarvis.dictionaryMethod.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jarvis.LCSMethod.LCSMethod;
import jarvis.dictionaryMethod.entity.*;
import jarvis.dictionaryMethod.exception.IncorrectFileException;



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

    public double checkForEquals(String first, String second){
        String[] firstArray = first.split(" ");
        String[] secondArray = second.split(" ");
        int resMatrix[][] = new int[firstArray.length][secondArray.length];
        for(int i = 0; i < firstArray.length; i++){
            for(int j = 0; j < secondArray.length; j++){
                resMatrix[i][j] = getRating(firstArray[i],secondArray[j]);
            }
        }
        int res = getMaxMatch(resMatrix);
        return res/Math.min(firstArray.length,secondArray.length);

    }

    public int getMaxMatch(int[][] resMatrix) {
        if(resMatrix[0].length<resMatrix.length)
            resMatrix = reverseMatrix(resMatrix);
        int[] res = new int[resMatrix[0].length];
        for(int i = 0; i <res.length; i++){
            int[][] matrixClone = deepCopy(resMatrix);
            int sum = resMatrix[0][i];
            clearColumnAndRow(matrixClone, 0, i);
            for(int j = 1; j<resMatrix.length;j++){
                int maxRowValue = getMaxValueIndex(resMatrix[j]);
                sum+= resMatrix[j][maxRowValue];
                clearColumnAndRow(matrixClone,j,maxRowValue);
            }
            res[i] = sum;


        }
        return Arrays.stream(res).max().orElse(0);
    }

    private int[][] reverseMatrix(int[][] matrix) {
        int[][] resultMatrix = new int[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                resultMatrix[j][i] = matrix[i][j];
            }
        }
        return resultMatrix;
    }

    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
    private int getMaxValueIndex(int[] resMatrix) {
        int maxIndex = 0;
        int max = resMatrix[maxIndex];

        for(int i= 0; i < resMatrix.length; i++){
            if(max<resMatrix[i]) {
                maxIndex = i;
                max = resMatrix[i];
            }
        }
        return maxIndex;
    }

    public void clearColumnAndRow(int[][] matrixClone, int n, int m) {

        if (n<matrixClone[0].length) {
            for (int i = 0; i < matrixClone.length; i++) {
                matrixClone[i][n] = 0;
            }
        }
        if(m<matrixClone.length) {
            for (int j = 0; j < matrixClone[0].length; j++) {
                matrixClone[m][j] = 0;
            }
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
        int s1Size = set1.size();
        int s2Size = set2.size();
        if(s1Size == 0 || s2Size == 0){
            return new LCSMethod().getRatingForTwoWords(s1,s2);
        }
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
