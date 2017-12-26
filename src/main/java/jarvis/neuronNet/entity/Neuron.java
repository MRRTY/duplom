package jarvis.neuronNet.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.concurrent.atomic.AtomicInteger;

public class Neuron {
    private static AtomicInteger counter = new AtomicInteger(0);
    private double value;
    private int id;

    public Neuron() {
        id = counter.getAndIncrement();
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
