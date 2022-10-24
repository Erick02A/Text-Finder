package Server;

public class Nodo {
    private String palabra;
    private String[] ocurrencias;
    private Nodo izquierdo;
    private  Nodo derecho;
    private int balance;
    public Nodo(String palabra, String[] ocurrens){
        this.palabra = palabra;
        this.ocurrencias= ocurrens;
        this.izquierdo = null;
        this.derecho = null;
        this.balance = 0;

    }
    public String getPalabra(){
        return palabra;
    }
    public String[] getOcurrencias(){
        return ocurrencias;
    }
    public void setPalabra(String palabra){
        this.palabra = palabra;
    }
    public void setOcurrencias(String[] ocurrencias){
        this.ocurrencias = ocurrencias;
    }
    public Nodo getIzquierdo(){
        return  izquierdo;
    }
    public Nodo getDerecho(){
        return derecho;
    }
    public void setIzquierdo(Nodo nodo){
        this.izquierdo = nodo;
    }
    public void setDerecho(Nodo nodo){
        this.derecho = nodo;
    }
    public int getBalance(){
        return balance;
    }
    public void setBalance(int num){
        this.balance = num;
    }
}
