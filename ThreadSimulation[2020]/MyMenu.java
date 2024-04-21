import javax.swing.*;
import java.awt.event.*;


public class MyMenu extends JMenuBar{

    private JMenu info;
    private JMenuItem about;
    private String InfoMassage = "";

    public MyMenu()
    {
        info = new JMenu("Info");
        about = new JMenuItem("O programie");

        InfoMassage+="'Symulacja Lista 5'\n";
        InfoMassage+="Autor: Illia Azler\n";
        InfoMassage+="Kontakt: 239537@student.pwr.edu.pl\n";
        InfoMassage+="Milego dnia ;)  ";

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        InfoMassage);
            }
        });
        info.add(about);

        this.add(info);
    }
}
