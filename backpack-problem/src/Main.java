import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int length = 100, width = 150, sizeRect = 50, lengthRect = 10, widthRect = 15;
        Random random = new Random();
        Container container = new Container(length, width);
        Rect[] rectangles = new Rect[sizeRect];
        BestGenome bestGenome = new BestGenome();
        for(int i = 0; i < 50; ++i)
        {
            rectangles[i] = new Rect(random.nextInt() % lengthRect, random.nextInt() % widthRect);
        }
        container.RandomFillPopulation(rectangles, bestGenome);

        for(int i = 0; i < 50; ++i)
        {
            container.NextPopulation(rectangles);
        }
    }
}
