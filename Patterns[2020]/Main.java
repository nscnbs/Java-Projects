import java.awt.*;
import javax.swing.JFrame;


public class Main extends JFrame {

    private Panel drawing;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Main() {
        super("MouseTest");

        add(new Panel());
        //drawing.addMouseListener(drawing);
        //setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}