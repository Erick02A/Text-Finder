package Server;

public class NodoA {
    private Arboles Arbol;
    private NodoA next;
    public NodoA(Arboles arbol){
        this.Arbol = arbol;
        this.next = null;
    }
    public NodoA getNext() {
        return next;
    }
    public void setNext(NodoA next) {
        this.next = next;
    }
    public Arboles getArbol() {
        return Arbol;
    }
}
