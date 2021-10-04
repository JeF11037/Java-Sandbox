import java.awt.Color;
import java.util.ArrayList;

public class Shape extends Point {
    
    private ArrayList<Point> shape = new ArrayList<Point>();

    public Shape(){
        super(0, 0, 0, Color.BLACK);
    }

    public ArrayList<Point> getShape(){
        return shape;
    }

    public void clearShape(){
        shape = new ArrayList<Point>();
    }

    public int getPointsCount() {
        return shape.size();
    }

    public int[] getAllX(){
        int[] allX = new int[shape.size()];
        for (int tick = 0; tick < shape.size(); tick++) {
            allX[tick] = shape.get(tick).getX();
        }
        return allX;
    }

    public int[] getAllY(){
        int[] allY = new int[shape.size()];
        for (int tick = 0; tick < shape.size(); tick++) {
            allY[tick] = shape.get(tick).getY();
        }
        return allY;
    }

    public void addPoint(int x, int y, int radius, Color color){
        shape.add(new Point(x, y, radius, color));
    }

}
