import java.awt.*;


public class Prostokat extends Figura {
    public int x1,x2;
    public int y1,y2;
    public int width;
    public int height;

    public Prostokat(int x1, int y1, int x2, int y2){
        //x1,y1 - lewy górny
        //x2,y2 - prawy dolny
        this.x1 = Math.min(x1,x2);
        this.x2 = Math.max(x1,x2);
        this.y1 = Math.min(y1,y2);
        this.y2 = Math.max(y1,y2);
        this.width = this.x2-this.x1;
        this.height = this.y2-this.y1;
    }


    public void draw(Graphics2D g){
        //ustawienie kolorow
        this.setColor(g);
        //rysowanie prostokata
        g.fillRect(x1,y1,width,height);
    }


    public void move(int dx, int dy){
        this.x1+=dx;
        this.y1+=dy;
        this.x2+=dx;
        this.y2+=dy;
    }

    public boolean inside(int x,int y){
        boolean check = (x>=x1 && x<=x2) && (y>=y1 && y<=y2);
        this.setSelect(check);
        return(check);
    }
}