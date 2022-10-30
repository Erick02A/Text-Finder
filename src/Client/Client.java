package Client;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.nio.file.Files;
import java.nio.file.Paths;

//import org.apache.poi.xwpf.usermodel.*;

/**
 * clase que se encarga de inicar y contener al cliente
 */
public class Client extends javax.swing.JFrame{
    private JButton eliminarButton;
    private JButton agregarButton;
    private JTextField textField1;
    private JButton buscarButton;
    private JComboBox Bibliotecas;
    private JPanel MainPanel;
    private JTextField textField;
    private static String Palabra = "Probando";

    final String HOST = "127.0.0.1";
    final int PORT = 5000;
    DataOutputStream out;
    DataInputStream in;

    public static String getPalabra() {
        return Palabra;
    }

    static JFrame frame = new Client("Text Finder");


    /**
     * Main del cliente para iniciar el mismo
     * @param args
     */
    public static void main(String[] args) {
        frame.setVisible(true);
    }
    /**
     * Metodo que se encarga de abrir el socket y enviar  un mensaje al servidor
     *
     */
    private void sockets(){
        try {
            String message = null;
            Socket clientSocket = new Socket(HOST, PORT);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF(Palabra);

             message = in.readUTF();
            if (message != null) {
                Palabra=message;
                System.out.println(message);
                Busqueda.main(null);
                frame.setVisible(false);
            }else{
                System.out.println(message);
                clientSocket.close();}

        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public Client(String title){
        super (title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();


        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();

                FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT *.PDF *.DOCX" ,"txt","pdf","docx");
                fc.setFileFilter(filtro);
                int selection = fc.showOpenDialog(fc);
                if(selection == JFileChooser.APPROVE_OPTION) {
                    File fichero = fc.getSelectedFile();
                    if (fichero.getName().contains(".txt")) {
                        try (FileReader fr = new FileReader(fichero)){
                            String cadena = "";
                            int valor = fr.read();
                            while(valor != -1){
                                cadena = cadena + (char) valor;
                                valor = fr.read();
                            }
                            //textArea.setText(cadena);
                            Bibliotecas.addItem(fichero.getName());
                            Archivo a = new Archivo(fichero.getName(),cadena);
                            Gson g = new Gson();
                            String json = g.toJson(a);
                            Palabra = json;
                            System.out.println(Palabra);
                            sockets();

                            } catch (IOException e1) {
                                e1.getStackTrace();
                            }

                    }else if (fichero.getName().contains(".pdf")){
                        try {
                            Bibliotecas.addItem(fichero.getName());
                            FileInputStream fis = new FileInputStream(fichero);

                            PDDocument pdfDocument = PDDocument.load(fis);
                            //System.out.println(pdfDocument.getPages().getCount());
                            PDFTextStripper pdfTextStripper = new PDFTextStripper();
                            Archivo a = new Archivo(fichero.getName(), pdfTextStripper.getText(pdfDocument).replaceAll("\r\n"," "));
                            Gson g = new Gson();
                            String json = g.toJson(a);
                            Palabra = json;
                            System.out.println(Palabra);
                            sockets();
                            pdfDocument.close();
                            fis.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }else if (fichero.getName().contains(".docx")){
                            try (XWPFDocument doc = new XWPFDocument(
                                    Files.newInputStream(fichero.toPath()))){
                                Bibliotecas.addItem(fichero.getName());
                                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
                                String docText = xwpfWordExtractor.getText();
                                Palabra = docText.replaceAll("\n"," ");

                                Archivo a = new Archivo(fichero.getName(), Palabra);
                                Gson g = new Gson();
                                String json = g.toJson(a);
                                Palabra = json;
                                System.out.println(Palabra);
                                sockets();

                            }catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        System.out.println("en implementacion");
                    }
                }

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Bibliotecas.getSelectedItem() != null) {
                    //Palabra = "eliminar";
                    //sockets();
                    //System.out.println(Bibliotecas.getSelectedItem());
                    //Palabra = (String) Bibliotecas.getSelectedItem();
                    //sockets();
                    Bibliotecas.removeItem(Bibliotecas.getSelectedItem());

                }else{JOptionPane.showMessageDialog(null,"Seleccione un archivo");}
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Inserte una palabra para buscar");

                }else {
                    if(null == Bibliotecas.getSelectedItem()) {
                        JOptionPane.showMessageDialog(null,"Agrege archivos a la biblioteca");
                    }else{
                        Palabra = "buscar";
                        sockets();
                        Palabra = textField1.getText();
                        sockets();


                    }
                }
            }
        });
    }

}
