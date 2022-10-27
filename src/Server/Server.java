package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

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
    private static ListaA lista = new ListaA();
    public static void main (String[] args){
        boolean creando = true;
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
                //System.out.println("Client connected");


                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String message = in.readUTF();
                System.out.println(message);

                out.writeUTF("Message Received");
                if (message.equals("buscar")){
                    creando = false;
                }else if (creando == true){
                    Gson g = new Gson();
                    Archivo a = g.fromJson(message, Archivo.class);
                    crea(a.getContenido(),a.getName());
                }else if(creando == false){
                    out.writeUTF(buscar(message));
                }
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,e);
        }
        //crea("los hombres son maquinas de jugar, amar y matar XD.","pepe.txt");
        //System.out.println("Se creo el arbol");
        //buscar("jugar");
    }
    public static void crea(String text, String archivo){
        Arboles arbol = new Arboles();
        String[] palabras =  text.split(" ");
        //System.out.println(palabras[3]);
        int cont = 1;
        for (String i: palabras){
            //i = i.replaceAll(".","");
            i = i.replaceAll(";","");
            i = i.replaceAll(":","");
            i = i.replaceAll(",","");
            //System.out.println(i);
            arbol.addNodo(i, new String[]{String.valueOf(cont), archivo});
            cont+=1;
        }
        NodoA a = new NodoA(arbol);
        lista.add(a);
        System.out.println("se creo el arbol");
    }
    public static String buscar(String palabra){
        NodoA current1 = lista.getHead();
        Nodo current = current1.getArbol().getRaiz();
        int cont = 1;
        Nodo nuevo = new Nodo(palabra,new String[] {""});
        while (true){
            List<Nodo> palabras = new ArrayList<>();
            palabras.add(current);
            System.out.println(current.getPalabra());
            palabras.add(nuevo);
            Collections.sort(palabras, new ComparaPalabra());
            if (Objects.equals(palabra,current.getPalabra())){
                //System.out.println("Encontrado, "+String.valueOf(cont));
                return "Enccontrado: "+String.valueOf(cont)+", "+current.getOcurrencias()[0]+", "+current.getOcurrencias()[1];
            }else if (palabras.get(0).getPalabra().equals(palabra)){
                if (current.getIzquierdo()!=null) {
                    System.out.println("Izquierda");
                    current = current.getIzquierdo();
                } else {
                    System.out.println("Cambia Arbol");
                    if (current1.getNext()!=null) {
                        current1 = current1.getNext();
                        current = current1.getArbol().getRaiz();
                    }else {
                        //System.out.println("no se encontro");
                        return "No se encontro";
                    }
                }
            }else if (palabras.get(1).getPalabra().equals(palabra)){
                if (current.getDerecho()!=null) {
                    System.out.println("Derecha");
                    current = current.getDerecho();
                }else {
                    System.out.println("Cambia Arbol");
                    if (current1.getNext()!=null) {
                        current1 = current1.getNext();
                        current = current1.getArbol().getRaiz();
                    }else {
                        //System.out.println("no se encontro");
                        return "No se encontro";
                    }
                }
            }
            cont+=1;
            System.out.println(" ");
        }

    }
}