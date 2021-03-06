package jarvis.neuronNet.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,   property = "id")
public class Synapse {
    private double weight;
    private Neuron leftNeuron;
    private Neuron rightNeuron;

    public Synapse() {
    }

    public Synapse(Neuron leftNeuron, Neuron rightNeuron) {
        this.leftNeuron = leftNeuron;
        this.rightNeuron = rightNeuron;
        weight = Math.random();
    }


    @Override
    public String toString() {
        return "\n"+"Synapse{" +
                "weight=" + weight +
                '}';
    }

    public Neuron getRightNeuron() {
        return rightNeuron;
    }

    public void setRightNeuron(Neuron rightNeuron) {
        this.rightNeuron = rightNeuron;
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
