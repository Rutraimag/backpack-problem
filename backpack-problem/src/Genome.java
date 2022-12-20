import java.util.ArrayList;
import java.util.Set;

public class Genome implements Comparable<Genome>{
    private ArrayList<Integer> ids;
    protected int square;
    protected double fitness;

    protected double partOfConteiner;

    public Genome()
    {
        square = 0;
        ids = new ArrayList<Integer>();
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

    public ArrayList<Integer> getIds() {
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

    @Override
    public int compareTo(Genome obj)
    {
        return Integer.compare(obj.square, this.square);
    }
}
