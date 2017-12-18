package jarvis.neuronNet.entity;

import jarvis.neuronNet.entity.Neuron;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons;

    public Layer() {
        neurons = new ArrayList<>();
    }

    public Layer(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    @Override
    public String toString() {
        return "\n"+"Layer{" +
                "neurons=" + neurons +
                '}';
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }
}
