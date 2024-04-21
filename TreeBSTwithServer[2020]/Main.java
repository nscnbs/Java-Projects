import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;


public class Main extends JFrame implements Runnable,ActionListener {

    /**
     * Zabiezpiecza połączenie z serwerem
     */
    static private Socket connection;

    /**
     * Strumień danych wyjściowych
     */
    static private ObjectOutputStream output;

    /**
     * Strumień danych wejściowych
     */
    static private ObjectInputStream input;

    /**
     * Typ: Integer,Double albo String
     */
    public static boolean integ = false;
    public static boolean doub = false;

    /**
     * Przechowywanie nazwy typu
     */
    public static String type;

    /**
     * Pole dla Input
     */
    private final JTextField t;

    /**
     * Przycisk Insert
     */
    private final JButton b1;

    /**
     * Przycisk Draw
     */
    private final JButton b2;

    /**
     * Przycisk Search
     */
    private final JButton b3;

    /**
     * Przycisk Delete
     */
    private final JButton b4;

    public static Tree tree;


    public static Object data;

    /**
     * Główna panel
     * @param name panel główna
     */
    public Main(String name) {
        super(name);
        setLocation(550, 320);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new JTextField(13);


        b1 = new JButton("Insert");
        b2 = new JButton("Draw");
        b3 = new JButton("Search");
        b4 = new JButton("Delete");

        b1.setBackground(Color.WHITE);
        b2.setBackground(Color.WHITE);
        b3.setBackground(Color.WHITE);
        b4.setBackground(Color.WHITE);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        add(t);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        setSize(220, 150);
    }

    /**
     * Uruchomienie, zaczynając od wyboru typu + server
     */
    public static void main(String[] args) {
        new Thread(new Server()).start();
        new ChooseType("Type");
    }

    /**
     * Uruchowmienie wątka klienta
     */
    @Override
    public void run() {
        try {
            while (true){
                connection = new Socket("127.0.0.1",4444);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null,input.readObject());
            }
        } catch (IOException e) {
            System.out.println("IO Exception in Main");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception in Main");
        }
    }

    /**
     * Umoźliwia wysyłanie danych na serwer
     *@param obj Wysłane dane
     */
    private static void sendData(Object obj) throws IOException {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Działanie z przyciskami
     * Działanie na drzewie
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String key = null;

        Object panelM = e.getSource();

        if (panelM == b1) {key="insert";}
        if (panelM == b2) {key="draw  ";}
        if (panelM == b3) {key="search";}
        if (panelM == b4) {key="delete";}

        try {
            sendData(key+t.getText());
            t.setText("");
        } catch (IOException ex) {
            System.out.println("IO Exception - Action in Main");
        }
    }
    static class ChooseType extends JFrame implements ActionListener {

        /**
         * Przycisk Integer
         */
        JButton b1;

        /**
         * Przycisk Double
         */
        JButton b2;

        /**
         * Przycisk String
         */
        JButton b3;


        /**
         * Panel wyboru typu
         * @param name panel wyboru typu
         */
        ChooseType(String name) {
            super(name);
            setLocation(550,320);
            setLayout(new GridLayout(3,1));
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            b1 = new JButton("Integer");
            b2 = new JButton("Double");
            b3 = new JButton("String");
            b1.setBackground(Color.WHITE);
            b2.setBackground(Color.WHITE);
            b3.setBackground(Color.WHITE);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            add(b1);
            add(b2);
            add(b3);
            setSize(220,150);
        }

        /**
         * Działanie z przyciskami
         * Ustawienie typu, uruchomienie główniej panel
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object panel1 = e.getSource();

            if(panel1 == b1) {
                integ = true;
                type = "Integer";
                Server.tree = new Tree<Integer>();
            }
            else if(panel1 == b2) {
                doub = true;
                type = "Double";
                Server.tree = new Tree<Double>();
            }
            else if(panel1 == b3) {
                type = "String";
                Server.tree = new Tree<String>();
            }
            setVisible(false);
            new Thread(new Main("Input")).start();
        }
    }
}