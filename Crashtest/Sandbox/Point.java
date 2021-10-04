import java.awt.Color;

public class Point {
    
    private int x = 0;
    private int y = 0;
    private int radius = 0;
    private Color color;

    public Point(int _x, int _y, int _radius, Color _color) {
        x = _x;
        y = _y;
        radius = _radius;
        color = _color;
    }
    
    public int getX(){
        return x;
    }

    public void setX(int value){
        x = value;
    }

    public int getY(){
        return y;
    }

    public void setY(int value){
        y = value;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int value){
        radius = value;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color value){
        color = value;
    }

    public int[] GetCoords() {
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;
        return coords;
    }
}
