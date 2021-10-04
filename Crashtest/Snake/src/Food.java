package src;

import java.awt.*;
import java.util.Random;

public class Food extends Drawable {
    private static Random rnd = new Random();

    public boolean exist = false;
    public Drawable food;

    public Food(int screenWidth, int screenHeight){
        super(rnd.nextInt(screenWidth), rnd.nextInt(screenHeight), 20, Color.RED);
    }

    public void spawn(int screenWidth, int screenHeight){
        do {
            setX(rnd.nextInt(screenWidth));
            setY(rnd.nextInt(screenHeight));
            System.out.println(getX() + ", " + getY());
        } while (getX() < 20 && getX() > screenWidth - 20 && getY() < 20 && getY() > screenHeight - 20);
    }
}
