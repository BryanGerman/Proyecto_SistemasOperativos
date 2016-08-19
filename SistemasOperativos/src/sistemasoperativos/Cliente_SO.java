/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasoperativos;


import java.io.*;
import java.net.Socket;

/**
 *
 * @author jake_
 */
public class Cliente_SO extends Thread {
 AES_SO aes=new AES_SO();
	String key = "1234567891234567";
 static final String HOST = "localhost";
 //static final String HOST = "172.31.103.81";
 static final int PUERTO=9000;
 
 public Cliente_SO( ) {
try{
Socket skCliente = new Socket( HOST , PUERTO );
InputStream ingreso = skCliente.getInputStream();
DataInputStream flujo = new DataInputStream( ingreso );
System.out.println( flujo.readUTF() );

OutputStream temp= skCliente.getOutputStream();
DataOutputStream salida = new DataOutputStream(temp);
String buscar="";
while(buscar.compareToIgnoreCase("fin")!=0){
	 buscar=palabraBuscar();
	 salida.writeUTF(buscar);
        String llegada =aes.decrypt(flujo.readUTF(), key);
	System.out.println(llegada);
}
//skCliente.close();
} catch( Exception e ) {
System.out.println( e.getMessage() );
}
 }
 
 public String palabraBuscar(){
	
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String salida="";
	try {
		salida = br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
			
		return  salida;
 }
 
 public static void main( String[] arg ) {
new Cliente_SO();
 }
 
}
