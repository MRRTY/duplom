package jarvis;

import jarvis.LCSMethod.LCSMethod;
import jarvis.dictionaryMethod.DictionaryMethod;
import jarvis.neuronNet.service.NetManager;

import java.io.IOException;
import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) throws IOException {
        NetManager nm = new NetManager("neuron.json");
        DictionaryMethod dm = new DictionaryMethod();
        LCSMethod lm = new LCSMethod();
        Scanner sc = new Scanner(System.in);
        while (true){
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            double dictionaryResult = dm.getResult(s1,s2);
            System.out.println(dictionaryResult);
            double lcsResult = lm.getResult(s1,s2);
            System.out.println(lcsResult);
            System.out.println(nm.check(new double[]{dictionaryResult,lcsResult}));
        }


    }
}
