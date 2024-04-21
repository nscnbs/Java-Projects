import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener,MouseWheelListener{
    JButton buttonOkrag = new JButton("Okrag");         //przycisk okrag
    JButton buttonProst = new JButton("Prostokat");       //przycisk prostokat
    JButton buttonTroj = new JButton("Trojkat");        //przycisk trojkat
    JButton buttonClear = new JButton("Clear");         //przycisk clear

    public JPopupMenu popup = new MyPopUp(this);


    public int mouseX, mouseY;
    public int countCircles = 0;
    public int countRectanges = 0;
    public int countTriangles = 0;

    public ArrayList<Figura> figury = new ArrayList<Figura>();
    public ArrayList<Integer> coordsOfCircle = new ArrayList<Integer>();
    public ArrayList<Integer> coordsOfRectangle = new ArrayList<Integer>();
    public ArrayList<Integer> coordsOfTrianglesX = new ArrayList<Integer>();
    public ArrayList<Integer> coordsOfTrianglesY = new ArrayList<Integer>();

    public MyPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setBackground(Color.WHITE);
        ActionListener act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                if (source == buttonOkrag) {
                    setCountCircles(getCountCircles() + 1);
                    System.out.println(countCircles);
                }
                else if (source == buttonProst) {
                    setCountRectanges(getCountRectanges() + 1);
                    System.out.println(countRectanges);
                }

                else if (source == buttonTroj) {
                    setCountTriangles(getCountTriangles() + 1);
                    System.out.println(countTriangles);
                }
                else if (source == buttonClear){
                    setFigury(new ArrayList<Figura>());
                    setCountCircles(0);
                    setCountRectanges(0);
                    setCountTriangles(0);
                    setCoordsOfTrianglesX(new ArrayList<Integer>());
                    setCoordsOfTrianglesY(new ArrayList<Integer>());
                    setCoordsOfCircle(new ArrayList<Integer>());
                    setCoordsOfRectangle(new ArrayList<Integer>());

                    repaint();
                }
            }
        };

        buttonOkrag.addActionListener(act);
        buttonProst.addActionListener(act);
        buttonTroj.addActionListener(act);
        buttonClear.addActionListener(act);

        this.setLayout(new FlowLayout());
        this.add(buttonOkrag);
        this.add(buttonProst);
        this.add(buttonTroj);
        this.add(buttonClear);
    }


    //Okrag
    public void setCoordsOfCircle(ArrayList<Integer> coordsOfCircle) {
        this.coordsOfCircle = coordsOfCircle;
    }

    public void setCountCircles(int countCircles) {
        this.countCircles = countCircles;
    }

    public int getCountCircles() {
        return countCircles;
    }


    //Prostokat
    public void setCoordsOfRectangle(ArrayList<Integer> coordsOfRectangle) {
        this.coordsOfRectangle = coordsOfRectangle;
    }

    public void setCountRectanges(int countRectanges) {
        this.countRectanges = countRectanges;
    }

    public int getCountRectanges() {
        return countRectanges;
    }


    //Trojkat
    public void setCoordsOfTrianglesX(ArrayList<Integer> coordsOfTrianglesX) {
        this.coordsOfTrianglesX = coordsOfTrianglesX;
    }

    public void setCoordsOfTrianglesY(ArrayList<Integer> coordsOfTrianglesY) {
        this.coordsOfTrianglesY = coordsOfTrianglesY;
    }

    public void setCountTriangles(int countTriangles) {
        this.countTriangles = countTriangles;
    }

    public int getCountTriangles() {
        return countTriangles;
    }


    //Figury
    public void setFigury(ArrayList<Figura> figury) {
        this.figury = figury;
    }

    public ArrayList<Figura> getFigury() {
        return figury;
    }




    public void addFigure(Figura figura) {
        for (Figura f : figury) {
            f.selectFalse();
        }
        figura.selectTrue();
        figury.add(figura);
        //"reset"
        repaint();
    }

    public void addCircle(int x_center, int y_center, int x_edge, int y_edge) {
        addFigure(new Okrag(x_center, y_center, x_edge, y_edge));
    }

    public void addRectangle(int x1, int y1, int x2, int y2) {

        addFigure(new Prostokat(x1, y1, x2, y2));
    }


    public void moved(int dx, int dy) {
        for (Figura f : figury) {
            //przesuwamy, kiedy figura jest zaznaczona
            if (f.getSelect()) f.move(dx, dy);
        }
        repaint();
    }


    /** Metoda rysujaca figure */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Figura f : figury)
            f.draw(g2d);
        for (int i = 1; i < coordsOfTrianglesX.size(); i++) {
            g2d.drawLine(coordsOfTrianglesX.get(i - 1), coordsOfTrianglesY.get(i - 1), coordsOfTrianglesX.get(i), coordsOfTrianglesY.get(i));
        }
    }


    /** Metoda umozliwiajaca obsluge klikania przyciskow myszki, wciśnięcie i zwolnienie przycisku*/
    public void mouseClicked(MouseEvent e) {
        //pobranie wspolrzednych figury
        int px = e.getX();
        int py = e.getY();
        System.out.println("mouseClicked");

        if (SwingUtilities.isRightMouseButton(e)) {
            for (Figura f : figury) {
                if (f.getSelect() && f.inside(px, py))
                    popup.show(this, px, py);
            }
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (countCircles > 0) {
                coordsOfCircle.add(px);
                coordsOfCircle.add(py);

                if (coordsOfCircle.size() == 4) {

                    addCircle(coordsOfCircle.get(0),coordsOfCircle.get(1),coordsOfCircle.get(2),coordsOfCircle.get(3));
                    countCircles -= 1;

                    coordsOfCircle = new ArrayList<Integer>();
                }
            }

            if (countRectanges > 0) {
                coordsOfRectangle.add(px);
                coordsOfRectangle.add(py);

                if (coordsOfRectangle.size() == 4) {
                    addRectangle(coordsOfRectangle.get(0), coordsOfRectangle.get(1), coordsOfRectangle.get(2), coordsOfRectangle.get(3));
                    countRectanges -= 1;

                    coordsOfRectangle = new ArrayList<Integer>();
                }
            }

            if (countTriangles > 0) {
                coordsOfTrianglesX.add(px);
                coordsOfTrianglesY.add(py);

                if (Math.abs(coordsOfTrianglesX.get(0) - px) < 5 &&
                        Math.abs(coordsOfTrianglesY.get(0) - py) < 5 && coordsOfTrianglesX.size() > 1) {
                    addFigure(new Trojkat(coordsOfTrianglesX, coordsOfTrianglesY));

                    countTriangles = 0;
                    coordsOfTrianglesX = new ArrayList<Integer>();
                    coordsOfTrianglesY = new ArrayList<Integer>();
                }

                repaint();
            }
        }

        for (Figura figura : figury) {
            figura.selectFalse();
        }

        //obsluga wyboru figury
        for (int i = figury.size() - 1; i >= 0; i--) {
            if (figury.get(i).inside(px, py)) {
                Figura f = figury.get(i);
                figury.remove(i);
                figury.add(f);
                figury.get(figury.size() - 1).selectTrue();
                break;
            }
        }

        repaint();
    }

    /** Metoda obslugi w obszarze nasłuchującym na figure */
    public void mouseEntered(MouseEvent e) {
    }


    /** Metoda obslugi opuszczenia obszaru figury */
    public void mouseExited(MouseEvent e) {
    }


    /** Metoda obslugi zwolnienia przyciskow myszki */
    public void mouseReleased(MouseEvent e) {
    }


    /** Metoda obslugi wcisniecia przyciskow myszki */
    public void mousePressed(MouseEvent e) {
        //wspolrzedne myszki
        mouseX = e.getX();
        mouseY = e.getY();
    }


    /** Metoda obslugi przesuniecia myszki */
    public void mouseMoved(MouseEvent e) {
    }


    /** Metoda obslugi kolka */
    public void mouseWheelMoved(MouseWheelEvent e) {
    }


    /** Metoda obslugi przeciagania figury */
    public void mouseDragged(MouseEvent e) {
        if (figury.get(figury.size() - 1).getSelect()) {

            //zmienne przechowujace nowe wspolrzedne myszki
            int newMouseX = e.getX();
            int newMouseY = e.getY();
            //wspolrzedne przesuniecia
            int x = 0, y = 0;

            //wartosc przesuniecia
            x += newMouseX - mouseX;
            y += newMouseY - mouseY;

            //przesuniecie figury
            moved(x, y);

            //aktualizacja pozycji
            mouseX = newMouseX;
            mouseY = newMouseY;
            repaint();
        }
    }
}