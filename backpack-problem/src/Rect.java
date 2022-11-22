public class Rect {
    private int length;
    private int width;
    private int square;
    private int id;
    private static int count = 0;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Rect(int length, int width) {
        this.length = length;
        this.width = width;
        square = width * length;
        count++;
        id = count;
    }

    public int getId() {
        return id;
    }

    public int getSquare() {
        return square;
    }
}
