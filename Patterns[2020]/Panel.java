import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Panel extends JPanel implements MouseListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int x, y;

    private int px, py;


    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Integer> coords = new ArrayList<Integer>();

    public Panel(){
        addMouseListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public int x1,x2;
    public int y1,y2;
    public int w;
    public int h;

    public void Prostokat(int x1, int y1, int x2, int y2){
        //x1,y1 - lewy górny
        //x2,y2 - prawy dolny
        this.x1 = Math.min(x1,x2);
        this.x2 = Math.max(x1,x2);
        this.y1 = Math.min(y1,y2);
        this.y2 = Math.max(y1,y2);
        this.w = this.x2-this.x1;
        this.h = this.y2-this.y1;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int px = e.getX();
        int py = e.getY();

        if (SwingUtilities.isLeftMouseButton(e)) {
            coords.add(px);
            coords.add(py);
            if (coords.size() == 4){
                Prostokat(coords.get(0),coords.get(1),coords.get(2),coords.get(3));
                coords = new ArrayList<Integer>();}
                /*
                for (int i=0; i<coords.size(); i++) {
                    System.out.println("x" + i);
                    System.out.println(": " + coords.get(i));
                }

                 */
        }

            repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        //points.add(new Point(x, y));
        //repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(Color.BLACK);
        drawRectangles(g2d);
    }

    private void drawRectangles(Graphics2D g2d) {
            g2d.fillRect(x1, y1, w, h);
        }
}