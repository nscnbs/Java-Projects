import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa implementująca funkcji serwera dla drzewa binarnego
 */
public class Server implements Runnable {

    /**
     * Drzewo binarne serwera
     */
    public static Tree tree;

    /**
     * Przyjmowane dane
     */
    private String MessageIn;

    /**
     * Instrukcji przesyłane wraz z elementem
     */
    private String key;

    /**
     * Wchodzący na server element typu String
     */
    private String element;

    /**
     * Wchodzący element
     */
    private Object data;

    /**
     * Serwer
     */
    private static ServerSocket server;

    /**
     * Zabiezpiecza połączenie z klientem
     */
    private static Socket connection;

    /**
     * Strumień danych wejściowych
     */
    private static ObjectInputStream input;

    /**
     * Strumień danych wyjściowych
     */
    private static ObjectOutputStream output;

    /**
     * Uruchowmienie wątka serwera
     */
    @Override
    public void run() {
        try {
            server = new ServerSocket(4444);
            while (true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

                MessageIn = (String) input.readObject();

                key = MessageIn.substring(0, 6);
                element = MessageIn.substring(6);

                data = element;

                if (key.equals("draw  ")) {
                    tree.draw();
                    new Draw();
                    Tree.elements.removeAll(Tree.elements);
                    Tree.numElements = 0;
                    output.writeObject("\nYour binary tree:\n" + tree);
                }
                try {
                    /**
                     * Sprawdza jakigo typu jest Input
                     */
                    if (Main.integ) {
                        data = Integer.parseInt(element);
                    } else if (Main.doub) {
                        data = Double.parseDouble(element);
                    }

                    /**
                     * Obsługa przycisków na serwerze
                     */
                    if (key.equals("insert")) {
                        if (tree.isElement((Comparable) data)) {
                            output.writeObject("<" + data + "> is already in the tree");
                        } else {
                            tree.insert((Comparable) data);
                            output.writeObject(Main.type + " <" + data + "> in");
                        }
                    }
                    if (key.equals("search")) {
                        if (tree.isElement((Comparable) data)) {
                            output.writeObject("<" + data + "> is in the tree");
                        } else {
                            output.writeObject("<" + data + "> is not in the tree");
                        }
                    }
                    if (key.equals("delete")) {
                        if (tree.isElement((Comparable) data)) {
                            tree.deleteElement((Comparable) data);
                            output.writeObject(Main.type + " <" + data + "> deleted");
                        } else {
                            output.writeObject("<" + data + "> does not exist in the tree");
                        }
                    }
                } catch (NumberFormatException e) {
                    try {
                        output.writeObject("Not this type of data!\n" +
                                "Your current type is: " + Main.type);
                    } catch (IOException e1) {
                        System.out.println("IO Exception e1 in Server");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception e in Server");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception in Server");
        }
    }

    /**
     * Panel dla wyświetlania drzewa
     */
    static class Draw{

        public String rowString = "";
        /**
         * Index odpowiedniego elementu w array dla "narysowania"
         */
        int j = 0;

        JFrame frame;

        Draw() {
            frame = new JFrame();
            JPanel panel = new JPanel();
            frame.add(panel);

            /**
             * Petla dla "narysowania" drzewa
             * Realizacja \n(<br>) za pomocą <html></html>
             * @param k - liczba działań
             */
            for (int i = 0; i < Tree.rows; i++) {
                rowString = rowString + "<html><center>";
                int k = (int) Math.pow(2, i);
                for (int v = 0; v < k; v++) {
                    if(j<Tree.elements.size()) {
                        rowString = rowString + Tree.elements.get(j) + " ";
                        j++;
                    }
                }
                rowString = rowString + "<br>";
            }
            rowString = rowString + "</html>";
            j = 0;
            JLabel label = new JLabel(rowString, SwingConstants.CENTER);
            label.setHorizontalTextPosition(JLabel.CENTER);
            panel.add(label);
            label.setFont(new Font("Trebuchet MS", 0, 30));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}