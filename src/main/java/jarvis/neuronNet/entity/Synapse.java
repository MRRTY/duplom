package jarvis.neuronNet.entity;

public class Synapse {
    private double weight;
    private Neuron leftNeuron;
    public Synapse(Neuron leftNeuron) {
        this.leftNeuron = leftNeuron;
        weight = Math.random();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Neuron getLeftNeuron() {
        return leftNeuron;
    }

    public void setLeftNeuron(Neuron leftNeuron) {
        this.leftNeuron = leftNeuron;
    }
}
