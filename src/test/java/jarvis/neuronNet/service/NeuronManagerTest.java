package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Neuron;
import jarvis.neuronNet.entity.type.NeuronType;
import org.junit.Test;

import static org.junit.Assert.*;

public class NeuronManagerTest {

    @Test
    public void createDefaultNet() {
        NeuronManager manager = new NeuronManager();
        Neuron n1 = manager.createAndReturnNeuron(NeuronType.INPUT);
        Neuron n2 = manager.createAndReturnNeuron(NeuronType.INPUT);
        Neuron n3 = manager.createAndReturnNeuron(NeuronType.OUTPUT);
        Neuron n4 = manager.createAndReturnNeuron(NeuronType.OUTPUT);
        manager.addSynapse(n3,n1);
        manager.addSynapse(n4,n1);
        manager.addSynapse(n3,n2);
        manager.addSynapse(n4,n2);


    }


}