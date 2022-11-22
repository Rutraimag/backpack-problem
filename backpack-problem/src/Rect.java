public class Rect {
    private int length;
    private int width;
    private int x;
    private int y;
    private int id;
    private static int count = 0;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect(int length, int width) {
        this.length = length;
        this.width = width;
        count++;
        id = count;
    }

    public int getId() {
        return id;
    }
}
