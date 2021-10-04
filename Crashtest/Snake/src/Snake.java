package src;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends Drawable {
    public ArrayList<Drawable> segments = new ArrayList<Drawable>();
    public String direction = "UP";

    public int segmentCount = 3;

    private int screenWidth;
    private int screenHeight;

    public Snake(int _screenWidth, int _screenHeight){
        super(0, 0, 0, Color.BLACK);
        screenWidth = _screenWidth;
        screenHeight = _screenHeight;
        for (int tick = 0; tick < segmentCount; tick++){
            segments.add(new Drawable((screenWidth/2)+(tick*20), screenHeight/2, 20, Color.GREEN));
        }
    }

    public void addSegment(){
        segments.add(new Drawable(segments.get(segments.size()-1).getX(), segments.get(segments.size()-1).getY(), 20, Color.GREEN));
        segmentCount++;
    }
}
