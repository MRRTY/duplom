package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Neuron;

import java.util.HashSet;
import java.util.Set;

public class NeuronsContainer {
    private Set<Neuron> allNeurons;

    public NeuronsContainer() {
        allNeurons = new HashSet<>();
    }

    public void addNeuron(Neuron neuron){
        allNeurons.add(neuron);
    }


    public Set<Neuron> getAllNeurons() {
        return allNeurons;
    }

    public void setAllNeurons(Set<Neuron> allNeurons) {
        this.allNeurons = allNeurons;
    }
}
