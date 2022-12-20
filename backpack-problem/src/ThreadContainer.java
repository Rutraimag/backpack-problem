import java.util.ArrayList;

public class ThreadContainer extends Thread{

    private Container _container;
    private ArrayList<Rect> _rectangles;
    ThreadContainer(String name, Container container, ArrayList<Rect> rectangles)
    {
        super(name);
        _container = container;
        _rectangles = rectangles;
    }
    @Override
    public void run()
    {
        _container.RandomFillPopulation(_rectangles);

        while(true)
        {
            _container.NextPopulation(_rectangles);
        }
    }
}
