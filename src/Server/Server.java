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

/**
 * Clase que se encarga de inicair el servidor y con esto esperar a lo que mande el cliente.
 */
public class Server {
    private static ListaA lista = new ListaA();

    /**
     * El main del servidor para que se inicie
     * @param args
     */
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
                if (message.equals("buscar")){
                    creando = false;
                }else if (creando == true){
                    Gson g = new Gson();
                    Archivo a = g.fromJson(message, Archivo.class);
                    crea(a.getContenido(),a.getName());
                }else if(creando == false){
                    out.writeUTF(buscar(message));
                    System.out.println(buscar(message));
                    creando = true;//uwu
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

    /**
     * Este metodo se encarga de crear un arbol binario y a la ves mete el arbol en una lista enlasada con el contenido de los archivos que envia el cliente
     * @param text texto que se encuentra el el archivo
     * @param archivo el nombre del archivo
     */
    public static void crea(String text, String archivo){
        Arboles arbol = new Arboles();
        String[] palabras =  text.split(" ");
        //System.out.println(palabras[3]);
        int cont = 1;
        for (String i: palabras){
            //i = i.replaceAll(". ","");
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

    /**
     * Metodo que se encarga de buscar una palabra que envie el cliente en los arboles que tenga ya guardados
     * @param palabra la palabra a buscar
     * @return
     */
    public static String buscar(String palabra){
        String mesage = "";
        boolean bandera = true;
        NodoA current1 = lista.getHead();
        Nodo current = current1.getArbol().getRaiz();
        int cont = 1;
        Nodo nuevo = new Nodo(palabra,new String[] {""});
        while (bandera){
            List<Nodo> palabras = new ArrayList<>();
            palabras.add(current);
            System.out.println(current.getPalabra());
            palabras.add(nuevo);
            Collections.sort(palabras, new ComparaPalabra());
            if (Objects.equals(palabra,current.getPalabra())){
                //System.out.println("Encontrado, "+String.valueOf(cont));
                mesage+= "Enccontrado:"+String.valueOf(cont)+","+current.getOcurrencias()[0]+","+current.getOcurrencias()[1]+"\n";
                if (current.getIzquierdo()!=null) {
                    current = current.getIzquierdo();
                }else {
                    if (current1.getNext()!=null){
                        current1 = current1.getNext();
                        current = current1.getArbol().getRaiz();
                    }else {
                        bandera = false;
                    }
                }
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
                        bandera = false;

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
                        bandera = false;
                    }
                }
            }
            cont+=1;
            System.out.println(" ");
        }
        return mesage;

    }
}