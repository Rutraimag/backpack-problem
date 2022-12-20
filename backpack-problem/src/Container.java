import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.exit;

public class Container {
    public static BestGenome bestGenome;
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

    public final int MAX_POP = 10000;

    public Genome[] population;

    public Container(int length, int width) {
        this.length = length;
        this.width = width;
        field = new int[length][width];
        GenerateField();
        population = new Genome[MAX_POP];
        bestGenome = new BestGenome();
    }

    private void GenerateField()
    {
        for(int i = 0; i < length; ++i)
        {
            for(int j = 0; j < width; ++j)
            {
                field[i][j] = 1;
            }
        }
    }

    private boolean Check(int x, int y, int _length, int _width)
    {
        if((x + _width > width) || (y - _length < 0) || (x < 0) || (y > length)) return false;
        for(int i = x; i < x + _width; ++i)
        {
            for(int j = y; j > y - _length; --j)
            {
                if(field[j][i] == 0) return false;
            }
        }
        return true;
    }

    private boolean SetOnField(int x, int y, int _length, int _width)
    {
        if(!Check(x, y, _length, _width)) return false;
        for(int i = x; i < x + _width; ++i)
        {
            for(int j = y; j > y - _length; --j)
            {
                field[j][i] = 0;
            }
        }
        return true;
    }

    public void RandomFillPopulation(ArrayList<Rect> rects)
    {
        Random random = new Random();
        for(int i = 0; i < MAX_POP; ++i)
        {
            population[i] = new Genome();
            for(int j = 0; j < rects.size(); ++j) {
                int x = Math.abs(random.nextInt() % width);
                int y = Math.abs(random.nextInt() % length);

                int id = Math.abs(random.nextInt() % rects.size());
                while (population[i] != null && population[i].checkUse(id)) id = (id + 1) % rects.size();
                if (SetOnField(x, y, rects.get(id).getLength(), rects.get(id).getWidth())) {
                    rects.get(j).setX0(x);
                    rects.get(j).setY0(y);
                    population[i].addIds(id);
                    population[i].setSquare(population[i].getSquare() + rects.get(id).getSquare());
                }
            }
            /*var tempRects = new ArrayList<Rect>();
            for(int j = 0; j < rects.length; ++j)
            {
                tempRects.add(rects[j]);
            }
            population[i] = Algorithm(tempRects);*/
            bestGenome.Check(population[i], field);
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

    public void NextPopulation(ArrayList<Rect> rects)
    {
        CalcFitness(population);
        Genome[] tempPopulation = new Genome[MAX_POP];

        int oldCounter = (int)(population.length * 0.2);

        Arrays.sort(population);

        for(int i = 0; i < oldCounter; ++i)
        {
            tempPopulation[i] = population[i];
            tempPopulation[MAX_POP - 1 - i] = population[MAX_POP - 1 - i];
        }

        for(int i = 0; i < population.length - oldCounter * 2; ++i)
        {
            tempPopulation[oldCounter + i] = generateNewGenome(population, rects);
        }
    }

    private Genome Algorithm(ArrayList<Rect> rects)
    {
        Collections.sort(rects);
        GenerateField();
        var levels = new ArrayList<Level>();
        Level level;
        Rect rect;
        var genome = new Genome();

        int high = length - 1;
        for(int i = 0; i < rects.size(); ++i)
        {
            boolean flag = false;
            rect = rects.get(i);
            for(int j = 0; j < levels.size(); ++j)
            {
                level = levels.get(j);
                if(SetOnField(level.left, level.low, rect.getLength(), rect.getWidth()))
                {
                    flag = true;
                    level.left += rect.getWidth();
                    break;
                }else if(SetOnField(level.right - rect.getWidth(), level.upper + rect.getLength(), rect.getLength(), rect.getWidth()))
                {
                    flag = true;
                    level.right -= rect.getWidth();
                    break;
                }
            }
            if(!flag)
            {
                if(SetOnField(0, high, rect.getLength(), rect.getWidth()))
                {
                    var newLevel = new Level();
                    newLevel.left = rect.getWidth();
                    newLevel.low = high;
                    newLevel.upper = high - rect.getLength();
                    newLevel.right = width;
                    high = newLevel.upper;
                    levels.add(newLevel);
                    flag = true;
                }
            }

            if(flag)
            {
                genome.addIds(i);
                genome.setSquare(genome.getSquare() + rect.getSquare());
            }
        }
        bestGenome.Check(genome, field);
        return genome;
    }


    private Genome generateNewGenome(Genome[] population, ArrayList<Rect> rects)
    {
        var rectsNotUsed = new ArrayList<Rect>(rects);
        Random random = new Random();
        int parent1Num = Math.abs(random.nextInt() % population.length);
        int parent2Num = Math.abs(random.nextInt() % population.length);
        parent2Num = parent2Num == parent1Num ? (parent2Num + 1) % population.length : parent2Num;

        var parent1 = population[parent1Num];
        var parent2 = population[parent2Num];

        double domination = Math.abs(random.nextInt() % 10000);
        double ligot = 2000;

        var tempRect = new ArrayList<Rect>();
        var ids = parent1.getIds();
        int tempCount = 0;
        for(int i = 0; i < ids.size(); ++i)
        {
            if(Math.abs(random.nextInt() % 10000) + ligot >= domination && !tempRect.contains(ids.get(i)))
            {
                tempRect.add(rects.get(ids.get(i)));
                rectsNotUsed.remove(rects.get(ids.get(i)));
                tempCount++;
            }
        }

        ids = parent2.getIds();
        boolean flag = false;
        for(int i = 0; i < ids.size(); ++i)
        {
            if((Math.abs(random.nextInt() % 10000) + ligot >= 10000-domination) || flag)
            {
                if(!tempRect.contains(ids.get(i)))
                {
                    tempRect.add(rects.get(ids.get(i)));
                    rectsNotUsed.remove(ids.get(i));
                    flag = false;
                }else flag = true;
            }
        }

        //mutation
        if(Math.abs(random.nextInt() % 100) <= 70)
        {
            int maxMutation = (int) Math.round(rectsNotUsed.size() * 0.1);
            int tempCounterMutation = Math.abs(random.nextInt() % 1000000000);
            while(tempCounterMutation > 0)
            {
                maxMutation--;
                tempCounterMutation /= 10;
            }
                Collections.shuffle(rectsNotUsed);
            for(int i = 0; i < Math.min(maxMutation, rectsNotUsed.size()); ++i)
            {
                tempRect.add(rectsNotUsed.get(i));
            }
        }

        return Algorithm(tempRect);
    }
}
