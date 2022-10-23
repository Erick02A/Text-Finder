package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static Arboles Arbol;
    public static void main (String[] args){
        /*ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream in;
        DataOutputStream out;
        final int PORT = 5000;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server in on!!");

            while (true) {
                clientSocket = serverSocket.accept();
                //System.out.println("Client connected");


                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String message = in.readUTF();
                System.out.println(message);

                out.writeUTF("Message Received");

                clientSocket.close();
                //System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,e);
        }*/
        crea("Los hombres son maquinas de jugar y matar XD","pepe.txt");
        System.out.println("Se creo el arbol");
        buscar("de");
    }
    public static void crea(String text, String archivo){
        Arbol = new Arboles();
        String[] palabras =  text.split(" ");
        //System.out.println(palabras[3]);
        int cont = 1;
        for (String i: palabras){
            //System.out.println(i);
            Arbol.addNodo(i, new String[]{String.valueOf(cont), archivo});
            cont+=1;
        }
    }
    public static void buscar(String palabra){
        Nodo current = Arbol.getRaiz();
        int cont = 1;
        boolean find = false;
        Nodo nuevo = new Nodo(palabra,new String[] {""});
        while (!find){
            List<Nodo> palabras = new ArrayList<>();
            palabras.add(current);
            palabras.add(nuevo);
            Collections.sort(palabras, new ComparaPalabra());
            if (Objects.equals(palabra,current.getPalabra())){
                System.out.println("Encontrado, "+String.valueOf(cont));
                find = true;
            } else if (palabras.get(0).getPalabra().equals(palabra)){
                System.out.println("Izquierda");
                current = current.getIzquierdo();
            }else if (palabras.get(1).getPalabra().equals(palabra)){
                System.out.println("Derecha");
                current = current.getDerecho();
            }
            cont+=1;
        }
    }
}
