import java.awt.*;


public class Okrag extends Figura {
    public int x,y; //center
    public int promien;


    public Okrag(int x1, int y1, int x2, int y2) {
        this.x = x1;
        this.y = y1;
        this.promien = (int)(Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2))));
    }


    public void draw(Graphics2D g) {
        //ustawienie kolorow
        this.setColor(g);
        //rysowanie kola
        g.fillOval(x-promien, y-promien, 2*promien, 2*promien);

    }


    public void move(int dx, int dy){
        this.x+=dx;
        this.y+=dy;
    }

    public boolean inside(int x_click, int y_click){
        int toCenter = (int)(Math.sqrt((this.x-x_click)*(this.x-x_click) + (this.y-y_click)*(this.y-y_click)));
        this.setSelect(promien>=toCenter);
        return promien>=toCenter;
    }
}