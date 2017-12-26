package jarvis.neuronNet.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Neuron {
    private static int counter = 0;
    private double value;
    private int id;

    public Neuron() {
        id = counter++;
    }


    @Override
    public String toString() {
        return "Neuron{" +
                "value=" + value +
                ", id=" + id +
                '}';
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
