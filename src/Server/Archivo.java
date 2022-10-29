package Server;

/**
 * Esta clase se encarga de crear un objeto tipo archivo para guardarlo en el json
 */
public class Archivo {
    private String name;
    private String contenido;
    public Archivo(String n,String c){
        this.name = n;
        this.contenido = c;
    }
    public String getName(){
        return name;
    }
    public String getContenido(){
        return contenido;
    }
}
