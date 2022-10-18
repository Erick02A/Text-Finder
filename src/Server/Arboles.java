package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arboles {
    private Nodo raiz;
    public Arboles(){
        this.raiz = null;
    }
    public void addNodo(String palabra, String[] ocurrencias){
        Nodo nuevo = new Nodo(palabra,ocurrencias);
        if (raiz==null){
            raiz= nuevo;
        }else {
            Nodo current = raiz;
            while (current!=nuevo) {
                List<Nodo> palabras = new ArrayList<>();
                palabras.add(current);
                palabras.add(nuevo);
                Collections.sort(palabras, new ComparaPalabra());

                if (palabras.get(0).getPalabra().equals(nuevo)) {
                    if (current.getIzquierdo()==null){
                        current.setIzquierdo(nuevo);
                    }
                    current = current.getIzquierdo();
                }else {
                    if(current.getDerecho()==null){
                        current.setDerecho(nuevo);
                    }
                    current = current.getDerecho();
                }
            }
        }
    }
}
