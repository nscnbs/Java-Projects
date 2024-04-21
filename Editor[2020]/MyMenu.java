import javax.swing.*;
import java.awt.event.*;


public class MyMenu extends JMenuBar{

    private JMenu info;
    private JMenuItem help, about;
    private String HelpMessage = "";
    private String InfoMassage = "";

    public MyMenu(MyPanel panel)
    {
        info = new JMenu("Info");
        help = new JMenuItem("Pomoc");
        about = new JMenuItem("O programie");

        InfoMassage+="'Edytor Graficzny Lista 4'\n";
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

        HelpMessage+="'Okrag'\n";
        HelpMessage+="Zeby utworzyc okrag trzeba najpierw wskazac srodek, pozniej promien,\n";
        HelpMessage+="za pomoca dwoch klikniec odpowiednio, lewym przyciskiem myszki.\n";
        HelpMessage+="'Prostokat'\n";
        HelpMessage+="Zeby utworzyc prostokat trzeba najpierw wskazac lewy gorny rog, pozniej prawy dolny rog,\n";
        HelpMessage+="za pomoca klikniec odpowiednio, lewym przyciskiem myszki.\n";
        HelpMessage+="'Trojkat'\n";
        HelpMessage+="Zeby utworzyc wielokat nalezy wskazac kilka punktow na ekranie i polaczyc ich liniami.\n";
        HelpMessage+="Zamykanie wielokata polega na kliknieciu blisko punktu poczatkowego.\n";
        HelpMessage+="'Clear'\n";
        HelpMessage+="Usuwa narysowane figury\n\n";
        HelpMessage+="UWAGA!\n";
        HelpMessage+="Zeby ponownie narysowac figure trzeba ponownie nacisnac odpowiedni przycisk figury.\n\n";

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        HelpMessage);
            }
        });
        info.add(help);

        this.add(info);
    }
}
