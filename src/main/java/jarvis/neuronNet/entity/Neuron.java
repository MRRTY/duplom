package jarvis.neuronNet.entity;

public class Neuron {
    private double value;

    public Neuron() {
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "value=" + value +
                '}';
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
