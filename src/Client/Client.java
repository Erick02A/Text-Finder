package Client;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;
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



    public static void main(String[] args) {

        JFrame frame = new Client("Text Finder");
        frame.setVisible(true);

    }

    private void sockets(){
        try {
            Socket clientSocket = new Socket(HOST, PORT);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF(Palabra);

            String message = in.readUTF();
            //System.out.println(message);

            clientSocket.close();
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

                FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT *.PDF" ,"txt","pdf","docx");
                fc.setFileFilter(filtro);
                int selection = fc.showOpenDialog(fc);
                if(selection == JFileChooser.APPROVE_OPTION) {
                    File fichero = fc.getSelectedFile();
                    Bibliotecas.addItem(fichero.getName());

                    //textField.setText(fichero.getAbsolutePath());
                    //System.out.println(fc.getName());
                    if (fichero.getName().contains(".txt")) {

                        try (FileReader fr = new FileReader(fichero)) {
                            String cadena = "";
                            int valor = fr.read();
                            while (valor != -1) {
                                cadena = cadena + (char) valor;
                                valor = fr.read();
                            }
                            //textArea.setText(cadena);

                            Palabra = cadena;
                            System.out.println(Palabra);
                            sockets();

                        } catch (IOException e1) {
                            e1.getStackTrace();
                        }

                    }else if (fichero.getName().contains(".pdf")){
                        try {
                            FileInputStream fis = new FileInputStream(fichero);

                            PDDocument pdfDocument = PDDocument.load(fis);
                            //System.out.println(pdfDocument.getPages().getCount());
                            PDFTextStripper pdfTextStripper = new PDFTextStripper();
                            Palabra = pdfTextStripper.getText(pdfDocument);
                            System.out.println(Palabra);
                            sockets();
                            pdfDocument.close();
                            fis.close();

                        } catch (IOException ex) {
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
                Bibliotecas.removeItem(Bibliotecas.getSelectedItem());
            }
        });
    }

}
