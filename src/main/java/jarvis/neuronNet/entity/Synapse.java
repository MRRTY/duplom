package jarvis.neuronNet.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Synapse synapse = (Synapse) o;

        if (Double.compare(synapse.weight, weight) != 0) return false;
        if (leftNeuron != null ? !leftNeuron.equals(synapse.leftNeuron) : synapse.leftNeuron != null) return false;
        return rightNeuron != null ? rightNeuron.equals(synapse.rightNeuron) : synapse.rightNeuron == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (leftNeuron != null ? leftNeuron.hashCode() : 0);
        result = 31 * result + (rightNeuron != null ? rightNeuron.hashCode() : 0);
        return result;
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
