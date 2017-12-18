package jarvis.neuronNet.entity;

import jarvis.neuronNet.entity.type.NeuronType;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private double value;
    private NeuronType type;
    private List<Synapse> leftSynapses;

    public Neuron(NeuronType type) {
        this.type = type;
        leftSynapses = new ArrayList<>();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public NeuronType getType() {
        return type;
    }

    public void setType(NeuronType type) {
        this.type = type;
    }

    public List<Synapse> getLeftSynapses() {
        return leftSynapses;
    }

    public void setLeftSynapses(List<Synapse> leftSynapses) {
        this.leftSynapses = leftSynapses;
    }
}
