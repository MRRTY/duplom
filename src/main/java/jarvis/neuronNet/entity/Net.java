package jarvis.neuronNet.entity;

import jarvis.neuronNet.service.NeuronManager;

public class Net {
    private NeuronManager neuronManager = NeuronManager.getInstance();

    public Net() {
    }

    public NeuronManager getNeuronManager() {
        return neuronManager;
    }

    public void setNeuronManager(NeuronManager neuronManager) {
        this.neuronManager = neuronManager;
    }
}
