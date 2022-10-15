package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arboles {
    private Nodo raiz;
    private String[] alfa= {"a","b",};
    public Arboles(){
        this.raiz = null;
    }
    public void addNodo(String palabra, String[] ocurrencias){
        if (raiz==null){
            raiz= new Nodo(palabra,ocurrencias);
        }else {
            List<Nodo> palabras = new ArrayList<>();
            palabras.add(raiz);
            palabras.add(new Nodo("aurelio",new String[]{"archivo.txt","5"}));
            for (Nodo n: palabras){
                System.out.println(n.getPalabra());
            }

            Collections.sort(palabras, new ComparaPalabra());

            for (Nodo n: palabras){
                System.out.println(n.getPalabra());
            }
            System.out.println(palabras.get(0).getPalabra());
        }
    }
}
