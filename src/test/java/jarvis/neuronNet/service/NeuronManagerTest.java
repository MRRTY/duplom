package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Net;
import jarvis.neuronNet.entity.Synapse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NeuronManagerTest {

    @Test
    public void createDefaultNet() {
      NetManager nm = new NetManager(new Net());
      nm.createNeurons(new int[]{2,4,1});
        for(int i = 0; i < 10_000; i++) {
          nm.learn(new double[]{0.70, 0.6}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.44, 0.23}, new double[]{1});
          nm.learn(new double[]{0.50, 0.4}, new double[]{1});
          nm.learn(new double[]{0.7, 0.3}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.12, 0.10}, new double[]{0});
          nm.learn(new double[]{0.05, 0}, new double[]{0});
          nm.learn(new double[]{0.05, 0.05}, new double[]{0});
          nm.learn(new double[]{0.70, 0.6}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.44, 0.23}, new double[]{1});
          nm.learn(new double[]{0.50, 0.4}, new double[]{1});
          nm.learn(new double[]{0.7, 0.3}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.12, 0.10}, new double[]{0});
          nm.learn(new double[]{0.05, 0}, new double[]{0});
          nm.learn(new double[]{0.05, 0.05}, new double[]{0});
          nm.learn(new double[]{0.70, 0.6}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.44, 0.23}, new double[]{1});
          nm.learn(new double[]{0.50, 0.4}, new double[]{1});
          nm.learn(new double[]{0.7, 0.3}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.12, 0.10}, new double[]{0});
          nm.learn(new double[]{0.05, 0}, new double[]{0});
          nm.learn(new double[]{0.05, 0.05}, new double[]{0});
          nm.learn(new double[]{0.70, 0.6}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.44, 0.23}, new double[]{1});
          nm.learn(new double[]{0.50, 0.4}, new double[]{1});
          nm.learn(new double[]{0.7, 0.3}, new double[]{1});
          nm.learn(new double[]{1, 1}, new double[]{1});
          nm.learn(new double[]{0.12, 0.10}, new double[]{0});
          nm.learn(new double[]{0.05, 0}, new double[]{0});
          nm.learn(new double[]{0.05, 0.05}, new double[]{0});
        }
        System.out.println(nm.toString());

      System.out.println(nm.check(new double[]{0.01,1}));
      System.out.println(nm.toString());
      ObjectMapper mapper = new ObjectMapper();
      try {
        mapper.writeValue(new File("neuron.json"), nm.getNet());

        nm.setNet(mapper.readValue(new File("neuron.json"),Net.class));

      } catch (IOException e) {

        System.out.println(e.getMessage());
     }
      System.out.println(nm.toString());
      System.out.println(nm.check(new double[]{1,1}));
      System.out.println(nm.toString());

    }



}