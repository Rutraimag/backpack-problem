import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int length = 100, width = 150, sizeRect = 1000, lengthRect = 15, widthRect = 20;
        Random random = new Random();
        ArrayList<Rect> rectangles = new ArrayList<Rect>(sizeRect);
        int sum = 0;
        for(int i = 0; i < sizeRect; ++i)
        {
            rectangles.add(new Rect(Math.abs(random.nextInt() % lengthRect) + 1, Math.abs(random.nextInt() % widthRect) + 1));
            sum += rectangles.get(i).getSquare();
        }
        for(int i = 0; i < 10; ++i)
        {
            Container container = new Container(length, width);
            var thread = new ThreadContainer(String.valueOf(i), container, rectangles);
            thread.start();
        }
    }
}
