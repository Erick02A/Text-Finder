package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame{
    private JButton eliminarButton;
    private JButton agregarButton;
    private JTextField textField1;
    private JButton buscarButton;
    private JComboBox Bibliotecas;
    private JPanel MainPanel;

    public static void main(String[] args) {

        JFrame frame = new Client("Text Finder");
        frame.setVisible(true);

        final String HOST = "127.0.0.1";
        final int PORT = 5000;
        DataOutputStream out;
        DataInputStream in;

        try {
            Socket clientSocket = new Socket(HOST, PORT);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("Hello world from client, test");

            String message = in.readUTF();
            System.out.println(message);

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
    }
}
