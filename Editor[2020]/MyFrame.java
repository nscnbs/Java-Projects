import javax.swing.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    public MyPanel panel;

    public MyFrame() {

        JFrame frame = new JFrame("Edytor Graficzny");
        JPanel Panel = new MyPanel();
        JMenuBar menuBar = new MyMenu(panel);


        frame.add(Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(300,100);
        frame.setVisible(true);
        frame.setJMenuBar(menuBar);
    }
}