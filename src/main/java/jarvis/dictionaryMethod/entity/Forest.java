package jarvis.dictionaryMethod.entity;

import java.util.Set;

public class Forest {
    private Set<Tree> treeSet;

    public Forest() {
    }

    public Forest(Set<Tree> treeSet) {
        this.treeSet = treeSet;
    }

    public Set<Tree> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(Set<Tree> treeSet) {
        this.treeSet = treeSet;
    }

    @Override
    public String toString() {
        return "Forest{" +
                "treeSet=" + treeSet +
                '}';
    }
}
