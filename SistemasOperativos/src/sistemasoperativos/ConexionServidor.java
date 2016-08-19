/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class ConexionServidor {
//clase que se utiliza para la conexion del servidor, el cual realiza la conexion con el cliente 
    static final int PUERTO = 9000;
    String palabra = "";
    Socket skCliente;

    public static void Conexionservidor() {

        try {
             ServerSocket skServidor = new ServerSocket(PUERTO);
            while (true) {
             //metodo accept que valida la conexion con el cliente  
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado");
                ServidorHilo hilo = new ServidorHilo(skCliente);
                hilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
