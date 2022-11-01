package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase que se encarga de crear la ventana y las partes donde se muestran los resultados de la busueda.
 */
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

    /**
     * Metodo que se encarga de inicialkizar el Jframe
     * @param title titulo de la ventana
     * @param Datos daos a mostrar
     */
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
                String[] finds = Datos.split("¬");
                String url = "";
                int i = 0;
                boolean f = true;
                while(f) {
                    if (i == (table1.getSelectedRow())) {
                        String[] dats = finds[i].split(",");
                        url = dats[3];
                        f = false;
                    }
                    i++;
                }
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe","/c",url);
                try {
                    p.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Metodo que rellena el Jtable con los datos.
     */
    public void createTable(){
        String[] finds = Dato.split("¬");
        Object[][] data = new Object[finds.length][4];
        for (int i=0;i<finds.length;i++) {
            String[] dats = finds[i].split(",");
            data[i][3] = dats[0];
            data[i][2] = dats[1];
            data[i][1] = dats[2];
            data[i][0] = dats[3];
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Archivo","Posicion","arbolBin","arbolAVL"}
        ));
    }
    public String creatext(String text, int pos){
        String mesage = "";
        int inicio = pos-10;
        int fin = pos+10;
        int cont = 0;
        String[] palabras = text.split(" ");
        while ((cont>inicio-1)&&(cont<fin+1)){
            if (cont==pos){
                mesage+=palabras[cont]+" ";
            }else {
                mesage+=palabras[cont]+" ";
            }
        }
        return mesage;
    }
}
