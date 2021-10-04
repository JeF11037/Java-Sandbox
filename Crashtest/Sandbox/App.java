import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;
import java.awt.*;

public class App extends Canvas {

    private static int screenWidth = 1200;
    private static int screenHeight = 800;
    private static int border = 10;

    private static int ticks = 10;

    private static boolean left = false;
    private static boolean right = false;

    private static int mouseX = screenWidth / 2;
    private static int mouseY = screenHeight / 2;

    private static int mouseXCopy = 0;
    private static int mouseYCopy = 0;
    private static int offsetX = 0;
    private static int offsetY = 0;

    private static Rigidbody body = new Rigidbody();

    private static Graphics graphic;

    public static void main(String[] args) {
        App app = new App();
        JFrame rootFrame = getTunedFrame();
        JComponent rootPane = getTunedPane(rootFrame);
        graphic = rootPane.getGraphics();

        rootFrame.add(app);

        while (true) {
            try {
                Thread.sleep(1 * ticks);
                body.setTime(body.getTime() + 0.025);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            if (left || right) {
                body.setTime(1);
                if (left) drag();
                if (right) { draw(); offsetX = 0; offsetY = 0; }
            } else  {
                useGravity();
            }
            clear();
            display();
        }
    }

    private static void clear(){
        graphic.clearRect(0, 0, screenWidth, screenWidth);
    }

    private static void useGravity(){
        for (Point point : body.getShape()) {
            if (point.getX() >= border && point.getX() <= screenWidth - border * 3){
                point.setX(point.getX() - (int)body.getImpulse(offsetX * 1.5, body.getShape().size()));
            }
            if (point.getY() >= border && point.getY() <= screenHeight - border * 5) {
                point.setY(point.getY() + (int)body.getGravity() - (int)body.getImpulse(offsetY * 1.5, body.getShape().size()));
            }
        }
    }

    private static void display() {
        graphic.drawPolyline(body.getAllX(), body.getAllY(), body.getPointsCount());
        graphic.setColor(body.getColor());
    }

    private static void drag(){
        if (mouseXCopy != mouseX || mouseYCopy != mouseY) {
            for (Point point : body.getShape()) {
                offsetX = mouseXCopy - mouseX;
                offsetY = mouseYCopy - mouseY;
                if (mouseXCopy != mouseX) {
                    if ((point.getX() - offsetX) >= border && (point.getX() - offsetX) <= screenWidth - border * 3){
                        point.setX(point.getX() - offsetX);
                    }
                }
                if (mouseYCopy != mouseY) {
                    if ((point.getY() - offsetY) >= border && (point.getY() - offsetY) <= screenHeight - border * 5) {
                        point.setY(point.getY() - offsetY);
                    }
                }
            }
        }
        mouseXCopy = mouseX;
        mouseYCopy = mouseY;
    }

    private static void draw(){
        body.addPoint(    mouseX, 
                           mouseY, 
                           10, 
                           Color.BLACK);
    }

    private static JComponent getTunedPane(JFrame rootFrame){
        //System.out.println("X : " + MouseInfo.getPointerInfo().getLocation().getX()+ ", Y : " + MouseInfo.getPointerInfo().getLocation().getY());
        JComponent pane = rootFrame.getRootPane();
        pane.addMouseListener(new MouseInputAdapter(){
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseXCopy = mouseX;
                mouseYCopy = mouseY;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    left = true;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    right = true;
                    body.clearShape();
                }
            }
        });
        pane.addMouseListener(new MouseInputAdapter(){
            public void mouseReleased(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseXCopy = mouseX;
                mouseYCopy = mouseY;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    left = false;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    right = false;
                }
            }
        });
        pane.addMouseMotionListener(new MouseInputAdapter(){
            public void mouseDragged(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
            }
            public void mouseMoved(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        pane.setFocusable(true);
        pane.requestFocusInWindow();
        return pane;
    }

    private static JFrame getTunedFrame(){
        JFrame frame = new JFrame();
        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }
}