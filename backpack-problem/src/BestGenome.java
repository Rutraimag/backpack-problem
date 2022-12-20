public class BestGenome extends Genome{

    private int[][] bestField;

    private Rect[] rects;
    public BestGenome() {
        partOfConteiner = -1;
        square = -1;
    }

    public Rect[] getRects() {
        return rects;
    }

    public void setRects(Rect[] rects) {
        this.rects = rects;
    }

    public int[][] getBestField() {
        return bestField;
    }

    public void setBestField(int[][] bestField) {
        this.bestField = bestField;
    }

    private void PrintField(int[][] field)
    {
        int count0 = 0;
        int count1 = 0;
        for(int i = 0; i < field.length; ++i)
        {
            for(int j = 0; j < field[i].length; ++j)
            {
                System.out.print(field[i][j]);
                if(field[i][j] == 1) count1++; else count0++;
            }
            System.out.println();
        }
        System.out.println(count0 + " " + count1);
    }

    public boolean Check(Genome container, int[][] field)
    {
        if(this.square < container.getSquare()){
            this.setSquare(container.getSquare());
            this.setFitness(container.getFitness());
            this.setBestField(field);
            this.setRects(rects);

            System.out.print("\n\n\n\n\n\n\n\n\n\n\n");

            PrintField(field);

            System.out.println(square + " from " + 100*150);

            return true;
        }
        return false;
    }
}
