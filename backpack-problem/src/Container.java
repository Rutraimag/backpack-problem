import java.util.Random;

public class Container {
    private int length;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    private int width;

    private int[][][] field;

    public final int MAX_POP = 200;

    public Genome[] population;

    public Container(int length, int width) {
        this.length = length;
        this.width = width;
        field = new int[length][width][2];
        GenerateField();
        population = new Genome[MAX_POP];
    }

    private void GenerateField()
    {
        for(int i = 0; i < length; ++i)
        {
            for(int j = 0; j < width; ++i)
            {
                field[i][j][0] = i + 1;
                field[i][j][1] = width - j;
            }
        }
    }

    public void RandomFillPopulation(Container[] containers)
    {
        Random random = new Random();
        for(int i = 0; i < MAX_POP; ++i)
        {
            int x = random.nextInt() % width;
            int y = random.nextInt() % length;
            int id = random.nextInt() % containers.length;
        }
    }
}
