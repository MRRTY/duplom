package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Neuron;
import jarvis.neuronNet.entity.Synapse;
import jarvis.neuronNet.entity.type.NeuronType;

public class NeuronManager {
    private NeuronsContainer container;

    public NeuronManager() {
        container = new NeuronsContainer();
    }

    public Neuron createAndReturnNeuron(NeuronType type){
        Neuron neuron = new Neuron(type);
        container.addNeuron(neuron);
        return neuron;
    }
    public void addSynapse(Neuron n1, Neuron n2){
        n1.getLeftSynapses().add(new Synapse(n2));
    }



    public void addSynapses(Neuron n1, Neuron[] n2) {
        for(Neuron n : n2){
            n1.getLeftSynapses().add(new Synapse(n));
        }
    }

    public NeuronsContainer getContainer() {
        return container;
    }

    public void setContainer(NeuronsContainer container) {
        this.container = container;
    }
}
