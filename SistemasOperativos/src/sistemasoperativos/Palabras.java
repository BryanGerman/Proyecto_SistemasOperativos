package sistemasoperativos;

public class Palabras {
    String palabra = "";

    public Palabras(String palabras) {
        this.palabra = palabras;
    }
    
    

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return this.getPalabra();
    }
    
    
}
