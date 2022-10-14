package Server;

import java.util.Comparator;

public class ComparaPalabra implements Comparator {
    @Override
    public int compare(Nodo nodo1, Nodo nodo2) {
        return nodo1.getPalabra().compareTo(nodo2.getPalabra());
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
