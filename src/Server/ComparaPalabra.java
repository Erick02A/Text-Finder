package Server;

import java.util.Comparator;

public class ComparaPalabra implements Comparator<Nodo> {
    @Override
    public int compare(Nodo nodo1, Nodo nodo2) {
        return nodo1.getPalabra().compareTo(nodo2.getPalabra());
    }
}
