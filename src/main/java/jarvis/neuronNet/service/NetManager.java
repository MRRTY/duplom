package jarvis.neuronNet.service;

import jarvis.neuronNet.entity.Net;
import jarvis.neuronNet.entity.type.NeuronType;

public class NetManager {
    private Net net;

    public NetManager(Net net) {
        this.net = net;
    }

    public void count(){
        net.getNeuronManager().getContainer().getAllNeurons().stream().filter(neuron -> neuron.getType() == NeuronType.OUTPUT).forEach(neuron -> {
            neuron.getLeftSynapses().stream().map(synapse -> synapse.getLeftNeuron().getValue()*synapse.getWeight()).forEach(res -> neuron.setValue(neuron.getValue()+res));
            neuron.setValue(function(neuron.getValue()));
        });
    }

    private double function(double value) {
        return 1/(1+Math.pow(Math.E,-3*value));
    }

    public Net getNet() {
        return net;
    }

    public void setNet(Net net) {
        this.net = net;
    }
}
