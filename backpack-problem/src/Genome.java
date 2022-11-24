import java.util.Set;

public class Genome {
    private Set<Integer> ids;
    private int square;
    private double fitness;

    protected double partOfConteiner;

    public Genome()
    {
        square = 0;
    }

    public void setPartOfConteiner(Container container) {
        this.partOfConteiner = container.CalcSquare() / square;
    }

    public double getPartOfConteiner() {
        return partOfConteiner;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
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

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
