import java.awt.*;


public abstract class Figura{
    private boolean select = false;
    private boolean green = false;
    private boolean blue = true;
    private boolean yellow = false;
    private boolean red = false;

    public boolean getSelect() {
        return select;
    }
    public void setSelect(boolean select) {
        this.select = select;
    }


    /** Metoda zaznaczajaca zieleny */
    public void setGreen() {
        green = true;
        red=false;
        yellow=false;
        blue=false;
    }

    /** Metoda zaznaczajaca niebieski */

    public void setBlue() {
        blue = true;
        red=false;
        yellow=false;
        green=false;
    }
    /** Metoda zaznaczajaca zolty */
    public void setYellow() {
        yellow = true;
        red=false;
        green=false;
        blue=false;
    }
    /** Metoda zaznaczajaca czerwony */
    public void setRed() {
        red = true;
        green=false;
        yellow=false;
        blue=false;
    }

    /** Metoda zaznaczajaca figure */
    public void selectTrue() {
        select = true;
    }

    /** Metoda odznaczajaca figure */
    public void selectFalse() {
        select = false;
    }

    /** Metoda okreslajaca czy miejsce, w ktore kliknelismy lezy wewnatrz figury */

    public abstract boolean inside(int px, int py);

    /** Metoda przesuwajaca figure */

    public abstract void move(int dx, int dy);


    public abstract void draw(Graphics2D s);

    //napisać metodę
    public void setColor(Graphics2D g) {
        //kolory w RGB
        Color figureGreenSelect = Color.GREEN;
        Color figureGreen = new Color(11, 77, 8);
        Color figureBlueSelect = Color.BLUE;
        Color figureBlue = new Color (7, 10, 48);;
        Color figureYellowSelect = Color.YELLOW;
        Color figureYellow = new Color (130, 130, 7);;
        Color figureRedSelect = Color.RED;
        Color figureRed = new Color (69, 3, 3);

        if (blue) {
            if (select)
                g.setColor(figureBlueSelect);
            else
                g.setColor(figureBlue);
        }

        if (green) {
            if (select)
                g.setColor(figureGreenSelect);
            else
                g.setColor(figureGreen);
        }

        if (red) {
            if (select)
                g.setColor(figureRedSelect);
            else
                g.setColor(figureRed);
        }
        if (yellow) {
            if (select)
                g.setColor(figureYellowSelect);
            else
                g.setColor(figureYellow);
        }
    }
}