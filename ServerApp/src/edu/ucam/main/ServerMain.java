package edu.ucam.main;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import edu.ucam.threads.ClientHandler;

public class ServerMain {
	
	/*
	 * Crea el server socket en el puerto 5000
	 * Nuevo hilo cliente por conexión recibida
	 */
	
	public static final int PORT = 8000;
	
	Hashtable<String, ClientHandler> conectedClients = new Hashtable<>();
	
	
	public void ejecutar() {
		
		ServerSocket serverSocket = null;
		
		try {
			
			 serverSocket = new ServerSocket(PORT);
			 
			 while(true) {
				 
				 System.out.println("Esperando Conexiones...");
				 
				 Socket socket = serverSocket.accept();
				 
				 ClientHandler clientHandler = new ClientHandler(socket, conectedClients);
				 
				 clientHandler.start();

					
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		(new ServerMain()).ejecutar();

	}

}
