package jarvis.dictionaryMethod.entity;

import java.util.Set;

public class Tree {
    private String mainValue;
    private Set<Meaning> net;

    public Tree() {
    }

    public Tree(String mainValue, Set<Meaning> net) {
        this.mainValue = mainValue;
        this.net = net;
        net.add(new Word(mainValue));
    }

    public Tree(Set<Meaning> net) {
        this.net = net;
    }

    public Set<Meaning> getNet() {
        return net;
    }

    public void setNet(Set<Meaning> net) {
        this.net = net;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "net=" + net +
                '}';
    }
}
