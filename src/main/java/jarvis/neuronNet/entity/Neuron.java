package jarvis.neuronNet.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.concurrent.atomic.AtomicInteger;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,   property = "@id")
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
