package Server;

public class Arboles {
    private Nodo raiz;
    public Arboles(){
        this.raiz = null;
    }
    public void addNodo(String palabra, String[] ocurrencias){
        if (raiz==null){
            raiz= new Nodo(palabra,ocurrencias);
        }else {

        }
    }
}
