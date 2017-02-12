package sistemasoperativos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SistemasOperativos {

    private static String cadena;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList<String> palabras = new ArrayList();
        ArrayList<String> aux = new ArrayList();
        FileReader f = new FileReader("entrada.txt");
        BufferedReader b = new BufferedReader(f);

        while ((cadena = b.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(cadena, " ");
            while (tokens.hasMoreTokens()) {
                aux.add(tokens.nextToken());
            }
        }
        String aux2 = "a";
        Set<String> buscador = new HashSet<>(aux);
        for (String busqueda : buscador) {
            if (busqueda.contains(aux2) && busqueda.indexOf(aux2) == 0) {
                palabras.add(busqueda + " : " + Collections.frequency(aux, busqueda));
            }
        }
        Collections.sort(palabras);
        for (String busqueda : palabras) {
            System.out.println(busqueda);
        }
    }
}