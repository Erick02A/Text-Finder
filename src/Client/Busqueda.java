package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busqueda extends javax.swing.JFrame{
    private JPanel Panel2;
    private JTable table1;
    private JButton ButtonVolver;
    static JFrame frame = new Busqueda("Text Finder");

    public static void main(String[] args) {
        frame.setVisible(true);

    }

    public Busqueda(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Panel2);
        this.pack();
        ButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client("Text Finder");
                frame.dispose();
                Client.main(null);
            }
        });
    }
}
