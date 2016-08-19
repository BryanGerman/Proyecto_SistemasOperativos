package servidorcliente.Cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ConexionClientes {

    static final String HOST = "localhost";
    static final int PUERTO = 9000;
    private ExecutorService ex;

    public static void Conexionclientes() {
        try {

            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            int a = 0;
            while (a != 10) {
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