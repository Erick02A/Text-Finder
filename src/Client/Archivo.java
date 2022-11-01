package Client;

/**
 * Clase utilizada para generar objetos archivos en los Json
 */
public class Archivo {
    private String name;
    private String contenido;
    public Archivo(String n, String c){
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
