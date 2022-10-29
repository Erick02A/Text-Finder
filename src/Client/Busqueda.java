package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busqueda extends javax.swing.JFrame{
    private JPanel Panel2;
    private JTable table1;
    private JButton ButtonVolver;
    private JScrollPane Tabla;
    private JButton Abrir;
    private static String Dato = "Hola";
    static JFrame frame = new Busqueda("Text Finder", Dato);

    public static void main(String[] args) {
        frame.setVisible(true);

    }

    public Busqueda(String title,String Datos) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Panel2);
        this.pack();
        System.out.println(Datos);
        createTable();


        //table1.addColumn("Nombre del archivo");
        ButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client("Text Finder");
                frame.dispose();
                Client.main(null);
            }
        });
        Abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Datos);
            }
        });
    }
    private void createTable(){
        table1.setModel(new DefaultTableModel(
                null,
                new String[]{"Nombre del pdf","Posicion de la palabra","Comparaciones"}

        ));

    }



}
