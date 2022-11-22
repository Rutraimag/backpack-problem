import java.util.Set;

public class Genome {
    private Set<Integer> ids;
    private int fitness;

    public Genome()
    {
        fitness = 0;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public boolean checkUse(int id)
    {
        return ids.contains(id);
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void addIds(int id) {
        ids.add(id);
    }
}
