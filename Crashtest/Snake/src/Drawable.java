package src;

import java.awt.Color;
import java.awt.*;

public class Drawable {
    private int x;
    private int y;

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

    private Color color = Color.BLACK;

    public Color getColor(){
        return color;
    }

    private void setColor(Color value){
        color = value;
    }

    private int radius;

    public int getRadius(){
        return radius;
    }

    private void setRadius(int value) {
        radius = value;
    }

    Graphics drawing;

    public Graphics initDrawing(){
        drawing.fillOval(getX(), getY(), getRadius(), getRadius());
        drawing.setColor(getColor());
        return drawing;
    }

    public Drawable(int _x, int _y, int _radius, Color _color){
        setX(_x);
        setY(_y);
        setRadius(_radius);
        setColor(_color);
    }
}
