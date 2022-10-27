package Server;

public class ListaA {
    private NodoA head;
    private NodoA last;

    public ListaA(){
        this.head = null;
        this.last = null;
    }
    public void add(NodoA a){
        if (head == null){
            head = a;
            last = head;
        }else {
            last.setNext(a);
            last = a;
        }
    }
    public NodoA getHead(){
        return head;
    }
}
