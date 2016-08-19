/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasoperativos;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jake_
 */
public class Servidor_SO //Se hereda de conexión para hacer uso de los sockets y demás
        //Se hereda de conexión para hacer uso de los sockets y demás
{

    static final int PUERTO = 9000;
    static int Host = 3;
    ExecutorService ex;

    @SuppressWarnings("resource")

    public Servidor_SO() {
        try {

            ex = Executors.newFixedThreadPool(Host);
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escuchando el puerto " + PUERTO);
            for(int i =0 ;i<3;i++){

            Socket skCliente = skServidor.accept();
                
            
            System.out.println("cliente conectado");
            OutputStream salida = skCliente.getOutputStream();
            InputStream entrada = skCliente.getInputStream();
            DataOutputStream flujo = new DataOutputStream(salida);
            flujo.writeUTF(" \nIngrese las palabras que Desea Buscar");

            //llegada de datos al server 
            DataInputStream ingreso = new DataInputStream(entrada);
            String llegada = (ingreso.readUTF());
            System.out.println(llegada);
            /////almacenar salida;

            //
            //System.out.println(llegada.toLowerCase().compareTo("fin")!=0);
            while (llegada.toLowerCase().compareTo("fin") != 0) {
                StringTokenizer tokens = new StringTokenizer(llegada, " ");

                //System.out.println(llegada);
                while (tokens.hasMoreTokens()) {
                    ex.execute(new HiloBuscar(tokens.nextToken(), skCliente));
                    llegada = (ingreso.readUTF());

                }
            

}
            
            skCliente.close();
            }
            
        } catch (Exception e) {

        }

    }

    public static void main(String[] arg) {
        new Servidor_SO();
    }

    class HiloBuscar implements Runnable {

        AES_SO aes = new AES_SO();
        String key = "1234567891234567";
        private String text;
        private Socket socket;
        private DataOutputStream flujo;

        public HiloBuscar(String text, Socket socket) throws IOException {
            this.text = text;
            this.socket = socket;
            this.flujo = new DataOutputStream(socket.getOutputStream());
        }

        @Override

        public void run() {
            try {
                System.out.println("buscado...." + text);

                String p = aes.encrypt(buscarpalabra(), key);
                flujo.writeUTF(p);
                //flujo.writeUTF(buscarpalabra());
            } catch (IOException ex) {
                Logger.getLogger(Servidor_SO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Servidor_SO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public String buscarpalabra() throws Exception {
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

                String aux2 = text;
                Set<String> buscador = new HashSet<>(aux);
                for (String busqueda : buscador) {
                    if (busqueda.indexOf(aux2) == 0) {
                        palabras.add(busqueda + " : " + Collections.frequency(aux, busqueda));
                    }

                    //if( busqueda.indexOf(aux2) == -1){
                    //    return palabra="palabra: "+text+" no encontrada";
                    //}
                    // return palabra="palabra: "+text+" no encontrada";
                }
                if (palabras.isEmpty()) {
                    palabra = "no encontrada";
                } else {
                    Collections.sort(palabras);
                    for (String busqueda : palabras) {
                        //System.out.println(busqueda);
                        palabra += busqueda + "\n";
                    }
                }
                //Thread.sleep(1000);
                br.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "error");
            }
            return palabra;
        }

    }
    
    

}
