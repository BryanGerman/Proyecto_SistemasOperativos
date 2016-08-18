package sistemasoperativos;

import java.util.Comparator;

public class Comparador implements Comparator {
    @Override
    public int compare(Object p1, Object p2) {
		int comparador =0;
                Palabras palabra1 = (Palabras)p1;
                Palabras palabra2 = (Palabras)p2;
                comparador = palabra1.getPalabra().compareToIgnoreCase(palabra2.getPalabra());
		return comparador;
	}
    
}
