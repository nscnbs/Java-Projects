import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MyPanel extends JPanel {

    /***
     * Parametry wymiarow siatki
     */
    public static final int n = 20;
    public static final int m = 10;

    /***
     * Rozmiar pojedynczej plytki
     */
    public static int sizeofTile = 30;

    /***
     * stala czasu opoznienia watka, 1000ms = 1s
     */
    public static final double k=1000;

    /***
     * Interpretacja prawdopodobienstwa, p=5 dla latwej pracy w zakresie od 0 do 10
     */

    public static int p = 5;

    /***
     * Tablica plytek klassy Tile
     */

    public static ArrayList<Tile> tiles = new ArrayList<Tile>();

    /***
     * Paramentry dla wymiarow planszy
     */
    private int width;

    private int height;


    public MyPanel() {
        /***
         * "Zabiezpieczenie" dla dopasowania wymiarow planszy
         */
        this.height = m*sizeofTile;

        while(this.height>700) {
            sizeofTile -= 1;
            this.height = m * sizeofTile;
        }
        this.height = m * sizeofTile;
        this.width= n * sizeofTile;

        /***
         * Ustalenie wymiarow
         */

        this.setPreferredSize(new Dimension(width, height));

        /***
         * Parametry dla wysokosci i dlugosci plytek zgodnie z wymiarami n i m planszy
         */
        int tileWidth = width / n;
        int tileHeight = height / m;

        /***
         * Wypewnienie tablicy plytek - siatki
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = i * tileWidth;
                int y = j * tileHeight;
                tiles.add(new Tile(x, y, tileWidth, tileHeight));
            }
        }
    }

    /***
     *  Tworzenie siatki plytek
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        for (Tile tile : tiles) {
            g.setColor(tile.setColor);
            g.fillRect(tile.tile.x, tile.tile.y,
                    tile.tile.width, tile.tile.height);
            repaint();
        }
    }
}