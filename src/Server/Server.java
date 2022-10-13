package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main (String[] args){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream in;
        DataOutputStream out;
        final int PORT = 5000;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server in on!!");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected");


                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String message = in.readUTF();
                System.out.println(message);

                out.writeUTF("Message Received");

                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,e);
        }
    }
}
