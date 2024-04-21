import java.awt.*;
import java.util.ArrayList;


public class Trojkat extends Figura {
    public int[] x1;
    public int[] y1;
    public int counttroj;


    Trojkat(ArrayList<Integer> arx, ArrayList<Integer> ary){
        this.x1 =  new int[arx.size()];
        this.y1 =  new int[ary.size()];
        this.counttroj = arx.size();


        for (int i = 0; i <arx.size() ; i++) {
            this.x1[i]=arx.get(i);
            this.y1[i]=ary.get(i);
        }

    }

    public void draw(Graphics2D g) {
        this.setColor(g);
        g.fillPolygon(x1, y1, 3);
    }


    public void move(int dx, int dy){
        for (int i = 0; i < counttroj; i++) {
            x1[i] += dx;
            y1[i] += dy;
        }
    }


    public boolean inside(int x1, int y1){
        return (new Polygon(this.x1,this.y1,3)).contains(x1,y1);
    }
}