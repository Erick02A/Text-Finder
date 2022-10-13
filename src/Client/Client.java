package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main (String[] args){
        final String HOST = "127.0.0.1";
        final int PORT = 5000;
        DataOutputStream out;
        DataInputStream in;

        try {
            Socket clientSocket = new Socket(HOST, PORT);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("Hello world from client");

            String message = in.readUTF();
            System.out.println(message);

            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
