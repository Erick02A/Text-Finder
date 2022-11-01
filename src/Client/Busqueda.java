package Client;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Clase que se encarga de crear la ventana y las partes donde se muestran los resultados de la busueda.
 */
public class Busqueda extends javax.swing.JFrame {
    private JPanel Panel2;
    private JTable table1;
    private JButton ButtonVolver;
    private JScrollPane Tabla;
    private JButton Abrir;
    private JButton fecha;
    private JButton nombre;
    private JButton tamaño;
    private static String Dato;
    private static JFrame frame;

    public static void main(String[] args) {
        try {
            frame = new Busqueda("Text Finder", Client.getPalabra());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        frame.setVisible(true);
    }

    /**
     * Metodo que se encarga de inicialkizar el Jframe
     *
     * @param title titulo de la ventana
     * @param Datos daos a mostrar
     */
    public Busqueda(String title, String Datos) throws FileNotFoundException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Panel2);
        this.pack();
        Dato = GetDato(Datos,0);
        System.out.println("Prueba: " + Dato);
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
                String[] finds = Dato.split("¬");
                String url = "";
                int i = 0;
                boolean f = true;
                while (f) {
                    if (i == (table1.getSelectedRow())) {
                        String[] dats = finds[i].split(",");
                        url = dats[4];
                        f = false;
                    }
                    i++;
                }
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                try {
                    p.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        tamaño.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenamientoRADIX radix;
            }
        });
        fecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] datos = Dato.split("¬");
                datos = BubbleSort.bubbleSort(datos);
                Dato="";
                for (String b:datos){
                    Dato += b+"¬";
                }
                createTable();


            }
        });
        nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringQuickSort Quick;
            }
        });
    }

    /**
     * Metodo que rellena el Jtable con los datos.
     */
    public void createTable() {
        String[] finds = Dato.split("¬");
        Object[][] data = new Object[finds.length][5];
        for (int i = 0; i < finds.length; i++) {
            String[] dats = finds[i].split(",");
            data[i][4] = dats[0];
            data[i][3] = dats[1];
            data[i][2] = dats[2];
            data[i][1] = dats[5];
            data[i][0] = dats[3];
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Archivo","Texto", "Posicion", "arbolBin", "arbolAVL"}
        ));
    }

    public String GetDato(String text, int pos) throws FileNotFoundException {
        String[] finds = text.split("¬");
        String url = "";
        String name = "";
        String NewDato = "";

        for (int i = 0; i < text.split("¬").length; i++) {
            String[] dats = finds[i].split(",");
            url = dats[4];
            name = dats[3];
            String mesage = "";
            String Palabra = "";
            pos = Integer.parseInt(dats[2]);
            int inicio = pos - 10;
            int fin = pos + 10;
            int cont = 0;

            if (name.contains(".txt")) {


                try (FileReader fr = new FileReader(url)) {

                    BasicFileAttributes attrs = Files.readAttributes(Path.of(url), BasicFileAttributes.class);
                    FileTime time = attrs.creationTime();
                    System.out.println(time);

                    String cadena = "";
                    int valor = fr.read();
                    while (valor != -1) {
                        cadena = cadena + (char) valor;
                        valor = fr.read();
                    }

                    String[] palabras = cadena.split(" ");
                    if (fin > palabras.length - 1) {
                        fin = palabras.length - 1;
                    }
                    while (cont < fin) {
                        if (cont >= inicio) {
                            if(cont==pos-1){
                                System.out.println(palabras.length);
                                mesage += "["+palabras[cont]+"]"+ " ";
                                System.out.println(cont);
                            }else {
                                System.out.println(palabras.length);
                                mesage += palabras[cont] + " ";
                                System.out.println(cont);
                            }
                        }
                        cont+=1;
                    }
                    String[] a = String.valueOf(time).replaceAll("-","").split("T");
                    NewDato +=finds[i]+","+mesage.replaceAll(",", "")+","+String.valueOf(palabras.length)+","+a[0]+"¬";


                } catch (IOException e1) {
                    e1.getStackTrace();
                }

            } else if (name.contains(".pdf")) {
                try {

                    FileInputStream fis = new FileInputStream(url);

                    BasicFileAttributes attrs = Files.readAttributes(Path.of(url), BasicFileAttributes.class);
                    FileTime time = attrs.creationTime();
                    System.out.println(time);

                    PDDocument pdfDocument = PDDocument.load(fis);
                    //System.out.println(pdfDocument.getPages().getCount());
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();


                    String[] palabras = pdfTextStripper.getText(pdfDocument).replaceAll("\r\n", " ").split(" ");
                    if(fin>palabras.length-1){
                        fin=palabras.length-1;
                    }
                    while (cont < fin) {
                        if (cont >= inicio) {
                            if(cont==pos-1){
                                System.out.println(palabras.length);
                                mesage += "["+palabras[cont]+"]"+ " ";
                                System.out.println(cont);
                            }else {
                                System.out.println(palabras.length);
                                mesage += palabras[cont] + " ";
                                System.out.println(cont);
                            }
                        }
                        cont+=1;
                    }
                    String[] a = String.valueOf(time).replaceAll("-","").split("T");
                    NewDato +=finds[i]+","+mesage.replaceAll(",", "")+","+String.valueOf(palabras.length)+","+a[0]+"¬";

                    pdfDocument.close();
                    fis.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else if (name.contains(".docx")) {
                try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Path.of(url)))) {
                    XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
                    String docText = xwpfWordExtractor.getText();
                    Palabra = docText.replaceAll("\n", " ");

                    BasicFileAttributes attrs = Files.readAttributes(Path.of(url), BasicFileAttributes.class);
                    FileTime time = attrs.creationTime();
                    System.out.println(time);


                    String[] palabras = Palabra.split(" ");
                    if(fin>palabras.length-1){
                        fin=palabras.length-1;
                    }
                    while (cont < fin) {
                        if (cont >= inicio) {
                            if(cont==pos-1){
                                System.out.println(palabras.length);
                                mesage += "["+palabras[cont]+"]"+ " ";
                                System.out.println(cont);
                            }else {
                                System.out.println(palabras.length);
                                mesage += palabras[cont] + " ";
                                System.out.println(cont);
                            }
                        }
                        cont+=1;
                    }
                    String[] a = String.valueOf(time).replaceAll("-","").split("T");
                    NewDato +=finds[i]+","+mesage.replaceAll(",", "")+","+String.valueOf(palabras.length)+","+a[0]+"¬";

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return NewDato;
    }
}

