import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class SocketClient extends Frame implements ActionListener {
    Label output;
    Button button;
    TextField input;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    SocketClient() {
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        input = new TextField(20);
        output = new Label();
        output.setBackground(Color.white);
        button = new Button("Send");
        button.addActionListener(this);

        setLayout(new GridLayout(3,1));
        add(input);
        add(button);
        add(output);
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == button) {
            // Wysylanie do serwera
            out.println(input.getText());
            try {
                // Odbieranie z serwera
                output.setText(in.readLine());
            }
            catch (IOException e) {
                System.out.println("Read failed"); System.exit(1);
            }
            input.setText("");
            input.requestFocus();
        }
    }

    public void listenSocket(){
        try {
            socket = new Socket("localhost", 4444);
            // Poіaczenie z socketem
            // Wysylanie do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    public static void main(String[] args){
        SocketClient frame = new SocketClient();
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        } );
        frame.pack();
        frame.setVisible(true);
        frame.listenSocket();
    }
}