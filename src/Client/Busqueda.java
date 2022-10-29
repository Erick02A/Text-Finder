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
    private static String Dato;
    private static JFrame frame;

    public static void main(String[] args){
        frame = new Busqueda("Text Finder", Client.getPalabra());
        frame.setVisible(true);
    }

    public Busqueda(String title,String Datos) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Panel2);
        this.pack();
        Dato = Datos;

        System.out.println("Prueba: " + Datos);



        createTable();

        //table1.addColumn("Nombre del archivo");
        ButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Client("Text Finder");
                frame.dispose();
                frame.remove(frame);
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
    public void createTable(){

        String[] finds = Dato.split(":");
        Object[][] data = new Object[finds.length][3];
        for (int i=0;i<finds.length;i++) {
            String[] dats = finds[i].split(",");
            data[i][0] = dats[2];
            data[i][1] = dats[1];
            data[i][2] = dats[0];

        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Nombre del pdf","Posicion de la palabra","Comparaciones"}

        ));
    }




}
