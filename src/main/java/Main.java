import jarvis.LCSMethod.LCSMethod;

public class Main {
    public static void main(String[] args){
        String s1 = "test is on!";
        String s2 = "testing! online!";
        System.out.println(new LCSMethod().getResult(s1,s2));
    }
}
