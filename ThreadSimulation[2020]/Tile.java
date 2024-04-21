import java.awt.*;
import java.util.Random;

public class Tile {
    public Rectangle tile;

    /***
     * Generator pseudolosowy, jedyny w aplikacji
     */

    Random rand = new Random();

    Color setColor;

    /***
     * Dokladniej w MyPanel "Wypewnienie tablicy plytek - siatki"
     * @param x koordynaty lewego gornego rogy
     * @param y
     * @param l dlugosc i wysokosc pojedynczej plytki
     * @param h
     * oraz ustalenie losowego koloru RGB
     */

    public Tile(int x, int y, int l, int h){
        tile = new Rectangle(x,y,l,h);
        setRandomColor();
    }

    /***
     * Generator losowego koloru RGB, wszystkie kolory
     */
    public void setRandomColor(){
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        setColor = new Color(r,g,b);
    }
}