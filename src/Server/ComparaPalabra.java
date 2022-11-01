package Server;

import java.util.Comparator;

/**
 * Clase diseñada para comparar alfabeticamente las palabras.
 */
public class ComparaPalabra implements Comparator<Nodo> {
    //@Override

    /**
     * Metodo que se encarga de comparar dos objetos.
     * @param nodo1 the first object to be compared.
     * @param nodo2 the second object to be compared.
     * @return entero de comparacion
     */
    public int compare(Nodo nodo1, Nodo nodo2) {
        return nodo1.getPalabra().compareTo(nodo2.getPalabra());
    }

}
