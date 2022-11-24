public class BestGenome extends Genome{

    private int[][] bestField;

    private Rect[] rects;
    public BestGenome() {

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
}
