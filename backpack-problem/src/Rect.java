public class Rect {
    private int length;
    private int width;
    private int square;
    private int id;

    private int x0;
    private int y0;

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getX0() {
        return x0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getY0() {
        return y0;
    }

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
