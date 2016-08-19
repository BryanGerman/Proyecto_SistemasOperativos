package servidorcliente.Cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

//clase utilizada para la conexion del cliente con el servidor
public class ConexionClientes {
//host utilizado que pude ser cambiado por la ip del servidor
    static final String HOST = "localhost";
    //puerto utilizado por el cliente para conectarse con el servidor
    static final int PUERTO = 9000;
    private ExecutorService ex;

    public static void Conexionclientes() {
        try {
//socket creado para el uso de la clase cliente 
            Socket skCliente = new Socket(HOST, PUERTO);
            //inputStream y outputStream para el envio del flujo 
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            int a = 0;
            while (a != 10) {
                //lazo de repeticion hasta el valor de 10 para el ingreso de las palabras 
                System.out.println(flujo.readUTF());
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                OutputStream aux2 = skCliente.getOutputStream();
                DataOutputStream flujo2 = new DataOutputStream(aux2);
                flujo2.writeUTF(br.readLine());
                System.out.println(flujo.readUTF());
                
                a++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
