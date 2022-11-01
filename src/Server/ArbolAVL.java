package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase que genera los arbolesAVL.
 */
public class ArbolAVL {
    private Nodo raiz;

    /**
     * Metodo que inicializa el arbol
     */
    public ArbolAVL(){
        this.raiz = null;
    }

    /**
     * Metodo para obtener la raiz del arbol.
     * @return Nodo del arbol
     */
    public Nodo getRaiz(){
        return raiz;
    }

    /**
     * Obtiene la altura del nodo.
     * @param a Nodo
     * @return numero con la altura
     */
    public int ObtenerDeap(Nodo a){
        if(a==null){
            return -1;
        }else {
            return a.getBalance();
        }
    }

    /**
     * Metodo que se encarga de hacer una rotacion a la izquierda
     * @param nodo Nodo de referencia
     * @return Nodo raiz del segmento
     */
    public Nodo rotacionIzquierda(Nodo nodo){
        Nodo aux = nodo.getIzquierdo();
        nodo.setIzquierdo(aux.getDerecho());
        aux.setDerecho(nodo);
        nodo.setBalance(Math.max(ObtenerDeap(nodo.getIzquierdo()),ObtenerDeap(nodo.getDerecho()))+1);
        aux.setBalance(Math.max(ObtenerDeap(aux.getIzquierdo()),ObtenerDeap(aux.getDerecho()))+1);
        return aux;
    }
    /**
     * Metodo que se encarga de hacer una rotacion a la derecha
     * @param nodo Nodo de referencia
     * @return Nodo raiz del segmento
     */
    public Nodo rotacionDerecha(Nodo nodo){
        Nodo aux = nodo.getDerecho();
        nodo.setDerecho(aux.getIzquierdo());
        aux.setIzquierdo(nodo);
        nodo.setBalance(Math.max(ObtenerDeap(nodo.getIzquierdo()),ObtenerDeap(nodo.getDerecho()))+1);
        aux.setBalance(Math.max(ObtenerDeap(aux.getIzquierdo()),ObtenerDeap(aux.getDerecho()))+1);
        return aux;
    }
    /**
     * Metodo que se encarga de hacer una rotacion doble a la izquierda
     * @param nodo Nodo de referencia
     * @return Nodo raiz del segmento
     */
    public Nodo rotacionDobleIzquierda(Nodo nodo){
        Nodo temp;
        nodo.setIzquierdo(rotacionDerecha(nodo.getIzquierdo()));
        temp = rotacionIzquierda(nodo);
        return temp;
    }
    /**
     * Metodo que se encarga de hacer una rotacion doble a la derecha.
     * @param nodo Nodo de referencia
     * @return Nodo raiz del segmento
     */
    public Nodo rotacionDobleDerecha(Nodo nodo){
        Nodo temp;
        nodo.setDerecho(rotacionIzquierda(nodo.getDerecho()));
        temp = rotacionDerecha(nodo);
        return temp;
    }

    /**
     * Metodo que se llama para añadir un nodo
     * @param palabra palabra del nodo
     * @param ocurrencias lista de datos de la palabra
     */
    public void addNodo(String palabra, String[] ocurrencias){
        Nodo nodo = new Nodo(palabra,ocurrencias);
        if(raiz == null){
            raiz = nodo;
        }else {
            raiz = incertar(nodo,raiz);
        }
    }

    /**
     * Metodo que se encarga de recorrer el arbol e incertar el lnodo en el respectivo lugar
     * @param nuevo Nodo nuevo
     * @param sub Nodo raiz de referencia por sub arboles.
     * @return Nuevo nodo raiz del sub arbol.
     */
    public Nodo incertar(Nodo nuevo,Nodo sub){
        Nodo nuevoPadre = sub;
        List<Nodo> palabras = new ArrayList<>();
        palabras.add(sub);
        palabras.add(nuevo);
        Collections.sort(palabras, new ComparaPalabra());
        if (palabras.get(0).getPalabra().equals(nuevo.getPalabra())){
            if(sub.getIzquierdo()==null){
                sub.setIzquierdo(nuevo);
            }else {
                sub.setIzquierdo(incertar(nuevo,sub.getIzquierdo()));
                if (ObtenerDeap(sub.getIzquierdo())-ObtenerDeap(sub.getDerecho())==2){
                    palabras.add(sub.getIzquierdo());
                    Collections.sort(palabras, new ComparaPalabra());
                    if (palabras.get(0).getPalabra().equals(nuevo.getPalabra())){
                        nuevoPadre = rotacionIzquierda(sub);
                    }else {
                        nuevoPadre = rotacionDobleIzquierda(sub);
                    }
                }
            }
        } else if (palabras.get(1).getPalabra().equals(nuevo.getPalabra())) {
            if(sub.getDerecho()==null){
                sub.setDerecho(nuevo);
            }else {
                sub.setDerecho(incertar(nuevo,sub.getDerecho()));
                if (ObtenerDeap(sub.getDerecho())-ObtenerDeap(sub.getIzquierdo())==2){
                    palabras.add(sub.getDerecho());
                    Collections.sort(palabras, new ComparaPalabra());
                    if (!Objects.equals(palabras.get(1).getPalabra(), nuevo.getPalabra())){
                        nuevoPadre = rotacionDerecha(sub);
                    }else {
                        nuevoPadre = rotacionDobleDerecha(sub);
                    }
                }
            }
        }
        if ((sub.getIzquierdo()==null) && (sub.getDerecho()!=null)){
            sub.setBalance(sub.getDerecho().getBalance()+1);
        } else if ((sub.getDerecho()==null) && (sub.getIzquierdo()!=null)) {
            sub.setBalance(sub.getIzquierdo().getBalance()+1);
        }else {
            sub.setBalance(Math.max(ObtenerDeap(sub.getIzquierdo()),ObtenerDeap(sub.getDerecho()))+1);
        }
        return nuevoPadre;
    }
}
