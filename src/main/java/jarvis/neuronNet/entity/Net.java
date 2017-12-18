package jarvis.neuronNet.entity;

import java.util.ArrayList;
import java.util.List;

public class Net {
    private List<Layer> layers;
    private List<Synapse> allSynapses;

    public Net() {
        layers = new ArrayList<>();
        allSynapses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Net{" +
                "layers=" + layers +
                ", allSynapses=" + allSynapses +
                '}';
    }

    public List<Synapse> getAllSynapses() {
        return allSynapses;
    }

    public void setAllSynapses(List<Synapse> allSynapses) {
        this.allSynapses = allSynapses;
    }

    public Layer getInputLayer(){
        return layers.get(0);
    }
    public Layer getOutputLayer(){
        return layers.get(layers.size()-1);
    }
    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }


}
