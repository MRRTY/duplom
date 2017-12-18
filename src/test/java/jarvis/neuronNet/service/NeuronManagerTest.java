package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Net;
import org.junit.Test;

public class NeuronManagerTest {

    @Test
    public void createDefaultNet() {
      NetManager nm = new NetManager(new Net());
      nm.createNeurons(new int[]{2,4,1});
        System.out.println(nm.toString());
        nm.learn(new double[]{0.70,0.6}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.44,0.23}, new double[]{1});
        nm.learn(new double[]{0.50,0.4}, new double[]{1});
        nm.learn(new double[]{0.7,0.3}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.12,0.10}, new double[]{0});
        nm.learn(new double[]{0.05,0}, new double[]{0});
        nm.learn(new double[]{0.05,0.05}, new double[]{0});
        nm.learn(new double[]{0.70,0.6}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.44,0.23}, new double[]{1});
        nm.learn(new double[]{0.50,0.4}, new double[]{1});
        nm.learn(new double[]{0.7,0.3}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.12,0.10}, new double[]{0});
        nm.learn(new double[]{0.05,0}, new double[]{0});
        nm.learn(new double[]{0.05,0.05}, new double[]{0});
        nm.learn(new double[]{0.70,0.6}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.44,0.23}, new double[]{1});
        nm.learn(new double[]{0.50,0.4}, new double[]{1});
        nm.learn(new double[]{0.7,0.3}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.12,0.10}, new double[]{0});
        nm.learn(new double[]{0.05,0}, new double[]{0});
        nm.learn(new double[]{0.05,0.05}, new double[]{0});
        nm.learn(new double[]{0.70,0.6}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.44,0.23}, new double[]{1});
        nm.learn(new double[]{0.50,0.4}, new double[]{1});
        nm.learn(new double[]{0.7,0.3}, new double[]{1});
        nm.learn(new double[]{1,1}, new double[]{1});
        nm.learn(new double[]{0.12,0.10}, new double[]{0});
        nm.learn(new double[]{0.05,0}, new double[]{0});
        nm.learn(new double[]{0.05,0.05}, new double[]{0});
        System.out.println(nm.toString());
        System.out.println(nm.check(new double[]{1,1}));
    }



}