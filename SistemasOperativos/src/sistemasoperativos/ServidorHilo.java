
package servidorcliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Brysn
 */
public class ServidorHilo extends Thread {

    Socket cliente;
//clase servidor, en la cual se implementa el algoritmo de busqueda y conteo de palabras en un archivo de texto
    public ServidorHilo(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    //metodo run que se ejecuta automaticamente al crear un hilo que realiza la asignacion de un nuevo cliente simultaneamente al servidor
    public void run() {
        
        String palabra = "";
        try {
            while (true) {
                
                OutputStream aux = cliente.getOutputStream();
                DataOutputStream flujo = new DataOutputStream(aux);
                flujo.writeUTF("\nEscriba una palabra para ser buscada:\n");
                InputStream aux2 = cliente.getInputStream();
                DataInputStream flujo2 = new DataInputStream(aux2);
                palabra = flujo2.readUTF();
                flujo.writeUTF(buscarpalabra(palabra));

                System.out.println(palabra);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//metodo que busca la palabra o palabras que empiezan con una determinada letra y cuenta cuantas veces se repite 
    public String buscarpalabra(String text) throws Exception {
        FileReader ingreso = null;
        String palabra = "", error = "";
        ArrayList<String> palabras = new ArrayList();
        ArrayList<String> aux = new ArrayList();
        error = "palabra: " + text + " no encontrada";
        try {
            String cadena = "";
            ingreso = new FileReader("entrada.txt");
            BufferedReader br = new BufferedReader(ingreso);
            while ((cadena = br.readLine()) != null) {
                cadena = cadena.replaceAll(",", "");
                cadena = cadena.replaceAll("-", "");
                cadena = cadena.replaceAll("\\.", "");
                cadena = cadena.replaceAll("\"", "");
                cadena = cadena.replaceAll("\'", "");
                cadena = cadena.replaceAll("\\?", "");
                cadena = cadena.replaceAll("¿", "");
                cadena = cadena.replaceAll(";", "");
                cadena = cadena.replaceAll("\\*", "");
                cadena = cadena.replaceAll("_", "");
                cadena = cadena.replaceAll("$", "");
                cadena = cadena.replaceAll("/", "");
                cadena = cadena.replaceAll(";", "");
                cadena = cadena.replaceAll("\\(", "");
                cadena = cadena.replaceAll("\\)", "");
                cadena = cadena.replaceAll("﻿", "");
                cadena = cadena.replaceAll("!", "");
                cadena = cadena.replaceAll("¡", "");
                cadena = cadena.replaceAll(":", "");
                StringTokenizer st = new StringTokenizer(cadena, " ");
                //if(st.nextToken().compareTo(text)==0){
                while (st.hasMoreTokens()) {
                    aux.add(st.nextToken());
                }
                //palabra=linea;
                //}
            }
//Utilizacion de las clases ArrayList y HashSet para la implementacion del servidor
            String aux2 = text;
            Set<String> buscador = new HashSet<>(aux);
            for (String busqueda : buscador) {
                if (busqueda.indexOf(aux2) == 0) {
                    palabras.add(busqueda + " : " + Collections.frequency(aux, busqueda));
                }
            }
            if (palabras.isEmpty()) {
                palabra = "palabra no encontrada";
            } else {
                Collections.sort(palabras);
                for (String busqueda : palabras) {
                     palabra += busqueda + "\n";
                }
            }
             br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "error");
        }
        return palabra;
    }
}

