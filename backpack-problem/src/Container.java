import java.util.Random;

import static java.lang.System.exit;

public class Container {
    private int length;

    private int width;

    public int CalcSquare(){
        return length * width;
    }
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    private int[][] field;

    public final int MAX_POP = 200;

    public Genome[] population;

    public Container(int length, int width) {
        this.length = length;
        this.width = width;
        field = new int[length][width];
        GenerateField();
        population = new Genome[MAX_POP];
    }

    private void GenerateField()
    {
        for(int i = 0; i < length; ++i)
        {
            for(int j = 0; j < width; ++i)
            {
                field[i][j] = 1;
            }
        }
    }

    private boolean SetOnField(int x, int y, int length, int width)
    {
        for(int i = x; i < x + width; ++i)
        {
            for(int j = y; j < j + length; ++j)
            {
                if(field[j][i] == 0) return false;
            }
        }
        for(int i = x; i < x + width; ++i)
        {
            for(int j = y; j < j + length; ++j)
            {
                field[j][i] = 0;
            }
        }
        return true;
    }

    public void RandomFillPopulation(Rect[] rects, BestGenome bestGenome)
    {
        Random random = new Random();
        for(int i = 0; i < MAX_POP; ++i)
        {
            for(int j = 0; j < rects.length; ++j) {
                int x = random.nextInt() % width;
                int y = random.nextInt() % length;

                int id = random.nextInt() % rects.length;
                while (population[i].checkUse(id)) id = (id + 1) % rects.length;
                if (SetOnField(x, y, rects[id].getLength(), rects[id].getWidth())) {
                    rects[j].setX0(x);
                    rects[j].setY0(y);
                    population[i].addIds(id);
                    population[i].setSquare(population[i].getSquare() + rects[id].getSquare());
                }
            }
            if(bestGenome.getPartOfConteiner() <= population[i].getPartOfConteiner()){
                bestGenome.setSquare(population[i].getSquare());
                bestGenome.setFitness(population[i].getFitness());
                bestGenome.partOfConteiner = population[i].partOfConteiner;
                bestGenome.setBestField(field);
                bestGenome.setRects(rects);
            }
            GenerateField();
        }
    }

    private void CalcFitness(Genome[] population)
    {
        int sum = 0;
        for(int i = 0; i < population.length; ++i)
        {
            sum += population[i].getSquare();
        }
        for(int i = 0; i < population.length; ++i)
        {
            population[i].setFitness(population[i].getSquare()/(double)sum);
        }
    }

    public void NextPopulation(Rect[] rects)
    {
        Random random = new Random();
        CalcFitness(population);
    }
}
