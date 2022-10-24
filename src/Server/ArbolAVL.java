package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbolAVL {
    private Nodo raiz;
    public ArbolAVL(){
        this.raiz = null;
    }
    public void add(String palabra, String[] ocurrencias){
        Nodo nodo = new Nodo(palabra,ocurrencias);
        if (raiz==null){
            raiz = nodo;
        }else {
            Nodo current = raiz;
            while (current!=nodo){
                List<Nodo> palabras = new ArrayList<>();
                palabras.add(current);
                palabras.add(nodo);
                Collections.sort(palabras, new ComparaPalabra());
                if (palabras.get(0).getPalabra().equals(palabra)) {
                    if (current.getIzquierdo()==null){
                        current.setIzquierdo(nodo);
                    }
                    current.setBalance(current.getBalance()-1);
                    current = current.getIzquierdo();
                    System.out.println("I");
                }else {
                    if(current.getDerecho()==null){
                        current.setDerecho(nodo);
                    }
                    current.setBalance(current.getBalance()+1);
                    current = current.getDerecho();
                    System.out.println("D");
                }
            }
        }
    }
}
