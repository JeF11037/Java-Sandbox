import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.Math;

import src.Drawable;
import src.Snake;
import src.Food;

public class App extends Canvas {

    private static boolean on = true;
    private static int speed = 1;
    private static int ticks = 60;

    private static int screenWidth = 600;
    private static int screenHeight = 560;

    private static ArrayList<Drawable> drawables;
    private static JComponent rootPane;
    private static Graphics draw;

    private static Snake snake;
    private static Food food;

    public static void main(String[] args){
        App app = new App();
        JFrame rootFrame = new JFrame();
        rootFrame.setSize(screenWidth, screenHeight + 40);
        rootFrame.setLocationRelativeTo(null);  
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setResizable(false);
        rootFrame.setVisible(true);
        snake = new Snake(screenWidth, screenHeight);
        food = new Food(screenWidth, screenHeight);
        drawables = snake.segments;
        rootPane = rootFrame.getRootPane();
        draw = rootPane.getGraphics();
        rootPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        rootPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        rootPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        rootPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");

        rootPane.getActionMap().put("RIGHT", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                snake.direction = "RIGHT";
            }
        });
        rootPane.getActionMap().put("LEFT", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                snake.direction = "LEFT";
            }
        });
        rootPane.getActionMap().put("UP", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                snake.direction = "UP";
            }
        });
        rootPane.getActionMap().put("DOWN", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                snake.direction = "DOWN";
            }
        });

        rootPane.setFocusable(true);
        rootPane.requestFocusInWindow();
        
        rootFrame.add(app);

        while(on) {
            try {
                Thread.sleep(1 * ticks);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            draw.clearRect(0, 0, screenWidth, screenWidth);
            checkCoincidende();
            moveSnake();
            paintSnake();
            paintFood();
            if (!food.exist){
                food.spawn(screenWidth, screenHeight);
                food.exist = true;
            }
        }
    }

    public static void checkCoincidende(){
        for (Drawable drawable : drawables) {
            ArrayList<Drawable> copy = new ArrayList<Drawable>(drawables);
            copy.remove(drawable);
            if (copy.contains(drawable)) {
                System.out.println("You lost !\nYour tail size : " + (snake.segmentCount-1));
                on = false;
            }
        }
    }

    public static void paintFood(){
        //System.out.println("paintFood started.\n");
        draw.setColor(food.getColor());
        draw.fillOval(food.getX(), food.getY(), food.getRadius(), food.getRadius());
        /*System.out.println( "X : " + food.getX() + 
                                ", Y : " + food.getY() + 
                                ", Radius : " + food.getRadius() + 
                                ", Color : " + food.getColor());
        System.out.println("\nFood creating done.");
        */
    }

    public static void moveSnake(){
        for (int tick = 0; tick < drawables.size(); tick++){
            if(tick == drawables.size() - 1) {
                //System.out.println("Snake - X : " + drawables.get(tick).getX() + ", Y : " + drawables.get(tick).getY());
                //System.out.println("Food - X : " + food.x + ", Y : " + food.y);
                //System.out.println("ABS - X : " + Math.abs(food.x - drawables.get(tick).getX()) + ", Y : " + Math.abs(food.y - drawables.get(tick).getY()));

                if (food.exist && Math.abs(drawables.get(tick).getX() - food.getX()) <= 20 && Math.abs(drawables.get(tick).getY() - food.getY()) <= 20){
                    //System.out.println("Match");
                    food.exist = false;
                    snake.addSegment();
                }

                if (drawables.get(tick).getX() >= screenWidth) {
                    drawables.get(tick).setX(0);
                }
                if (drawables.get(tick).getX() <= -1) {
                    drawables.get(tick).setX(screenWidth);
                }
                if (drawables.get(tick).getY() >= screenHeight) {
                    drawables.get(tick).setY(0);
                }
                if (drawables.get(tick).getY() <= -1) {
                    drawables.get(tick).setY(screenHeight);
                }
                switch (snake.direction) {
                    case "LEFT":
                    drawables.get(tick).setX(drawables.get(tick).getX() - (speed*drawables.get(tick).getRadius()));
                    break;
                    case "RIGHT":
                    drawables.get(tick).setX(drawables.get(tick).getX() + (speed*drawables.get(tick).getRadius()));
                    break;
                    case "UP":
                    drawables.get(tick).setY(drawables.get(tick).getY() - (speed*drawables.get(tick).getRadius()));
                    break;
                    case "DOWN":
                    drawables.get(tick).setY(drawables.get(tick).getY() + (speed*drawables.get(tick).getRadius()));
                    break;
                }
            } else {
                drawables.get(tick).setX(drawables.get(tick + 1).getX());
                drawables.get(tick).setY(drawables.get(tick + 1).getY());                                         
            }
        }
    }

    public static void paintSnake(){
        //System.out.println("paintDrawable started.\n");
        for (Drawable drawable: drawables){
            draw.setColor(drawable.getColor());
            draw.fillOval(drawable.getX(), drawable.getY(), drawable.getRadius(), drawable.getRadius());
            /*System.out.println( "X : " + drawable.getX() + 
                                ", Y : " + drawable.getY() + 
                                ", Radius : " + drawable.getRadius() + 
                                ", Color : " + drawable.getColor());
                                */
        }
        //System.out.println("\nPainting done.");
    }
}