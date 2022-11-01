package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que se encarga de generar los arboles binarios de busqueda.
 */
public class Arboles {
    private Nodo raiz;

    /**
     * Contructor del arbol.
     */
    public Arboles(){
        this.raiz = null;
    }

    /**
     * Metodo que se encarga de recorrer el arbol y añadir el nuevo nodo donde corresponde.
     * @param palabra Palabra del nodo.
     * @param ocurrencias lista de datos de la palabra.
     */
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

                if (palabras.get(0).getPalabra().equals(palabra)) {
                    if (current.getIzquierdo()==null){
                        current.setIzquierdo(nuevo);
                    }
                    current = current.getIzquierdo();
                    //System.out.println("I");
                }else {
                    if(current.getDerecho()==null){
                        current.setDerecho(nuevo);
                    }
                    current = current.getDerecho();
                    //System.out.println("D");
                }
            }
        }
    }

    /**
     * Metodo para obtener la raizz del arbol
     * @return Nodo raiz.
     */
    public Nodo getRaiz(){
        return raiz;
    }
}
