package jarvis.neuronNet.service;

import jarvis.dictionaryMethod.exception.IllegalInputArgsException;
import jarvis.dictionaryMethod.exception.NotAllowedAgrsException;
import jarvis.neuronNet.entity.Layer;
import jarvis.neuronNet.entity.Net;
import jarvis.neuronNet.entity.Neuron;
import jarvis.neuronNet.entity.Synapse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NetManager {
    private Net net;

    public NetManager(Net net) {
        this.net = net;
    }

    public NetManager() {
    }

    private double function(double value) {
        return 1/(1+Math.pow(Math.E,-3*value));
    }

    public void createNeurons(int[] args){
        if(!(args.length < 2)){
            Layer inputLayer = new Layer();
            for (int i = 0; i < args[0]; i++){
                inputLayer.getNeurons().add(new Neuron());
            }
            net.getLayers().add(inputLayer);
            for (int i = 1; i < args.length-1; i++){
                Layer innerLayer = new Layer();
                for (int j = 0; j < args[i]; j++){
                    innerLayer.getNeurons().add(new Neuron());
                }
                joinLayers(net.getLayers().get(i-1),innerLayer);
                net.getLayers().add(innerLayer);
            }
            Layer outputLayer = new Layer();
            for (int i = 0; i < args[args.length-1]; i++){
                outputLayer.getNeurons().add(new Neuron());
            }
            joinLayers(net.getLayers().get(net.getLayers().size()-1),outputLayer);
            net.getLayers().add(outputLayer);
        }else {
            throw new NotAllowedAgrsException();
        }
    }

    public void learn(double[] input, double[] results){
        if(input.length!= net.getInputLayer().getNeurons().size()|| results.length != net.getOutputLayer().getNeurons().size()){
            throw new IllegalInputArgsException();
        }
        for(int i = 0; i<input.length; i++){
            net.getInputLayer().getNeurons().get(i).setValue(input[i]);
        }
        recount();
        Map<Neuron, Double> deltas = new HashMap<>();
        int index = 0;
        for(Neuron neuron : net.getOutputLayer().getNeurons()){
            deltas.put(neuron,getDeltaForOutputNeuron(neuron,results[index++]));
        }
        for(int i = net.getLayers().size()-2; i >= 0; i--){
            for(Neuron neuron : net.getLayers().get(i).getNeurons()){
                deltas.put(neuron,getDeltaForInnerNeuron(neuron,deltas));
            }
        }

        for(Synapse synapse: net.getAllSynapses()){
            synapse.setWeight(0.07*deltas.get(synapse.getRightNeuron())*synapse.getLeftNeuron().getValue() + synapse.getWeight());
        }
    }

    public double check(double[] input){
        if(input.length!= net.getInputLayer().getNeurons().size()){
            throw new IllegalInputArgsException();
        }
        for(int i = 0; i<input.length; i++){
            net.getInputLayer().getNeurons().get(i).setValue(input[i]);
        }
        recount();
        return net.getOutputLayer().getNeurons().get(0).getValue();
    }
    private List<Synapse> getSynapseWithLeftNeuron(Neuron neuron) {
        return net.getAllSynapses().stream().filter(synapse -> synapse.getLeftNeuron().equals(neuron)).collect(Collectors.toList());
    }

    private void recount() {
        for (int i = 1; i < net.getLayers().size(); i++){
            for (Neuron neuron:net.getLayers().get(i).getNeurons()){
                neuron.setValue(countForOneNeuron(neuron));
            }
        }
    }
    private double getDeltaForInnerNeuron(Neuron neuron, Map<Neuron,Double> deltas){
        double out = neuron.getValue();
        double sum = 0;
        for(Synapse synapse: getSynapseWithLeftNeuron(neuron)){
            sum += synapse.getWeight()*deltas.get(synapse.getRightNeuron());
        }
        return (1 - out)*out*sum;

    }
    private double getDeltaForOutputNeuron(Neuron neuron, double res){
        double out = neuron.getValue();
        return  (res - out)*(1 - out)*out;
    }
    private double countForOneNeuron(Neuron neuron) {
        double res = 0;
        List<Synapse> synapses = net.getAllSynapses().stream().filter(synapse -> synapse.getRightNeuron().getId() == neuron.getId()).collect(Collectors.toList());
        for(Synapse s: synapses){
            res+=s.getWeight()*s.getLeftNeuron().getValue();
        }
        return function(res);
    }

    private void joinLayers(Layer layer1, Layer layer2) {
        for(Neuron neuron : layer2.getNeurons()){
            for(Neuron connectingNeuron : layer1.getNeurons()){
                net.getAllSynapses().add(new Synapse(connectingNeuron,neuron));
            }
        }
    }

    @Override
    public String toString() {
        return "NetManager{" +
                "net=" + net +
                '}';
    }

    public Net getNet() {
        return net;
    }

    public void setNet(Net net) {
        this.net = net;
    }
}
