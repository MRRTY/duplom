package jarvis.LCSMethod.service;

public class WordManager {

    public int getRating(String s1, String s2){
        return method(s1, s2);

    }
    private int method(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return 0;
        }

        if (a.equals(b)) {
            return 100;
        }

        int[][] matrix = new int[a.length()][];

        int maxLength = 0;
        int maxI = 0;

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[b.length()];
            for (int j = 0; j < matrix[i].length; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i != 0 && j != 0) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = 1;
                    }
                    if (matrix[i][j] > maxLength) {
                        maxLength = matrix[i][j];
                        maxI = i;
                    }
                }
            }
        }
        String min = a.length()>b.length() ? b : a;
        return (a.substring(maxI - maxLength + 1, maxI + 1).length()*100/min.length());
    }
}
