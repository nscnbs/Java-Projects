import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        /***
         * GUI
         */

        JFrame frame = new JFrame("Symulacja");
        MyPanel panel = new MyPanel();


        frame.add(panel);
        frame.setLocation(100,40);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}