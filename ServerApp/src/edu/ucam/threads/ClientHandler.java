package edu.ucam.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;

import edu.ucam.logic.ProtocolParser;

public class ClientHandler extends Thread{
	
	/*
	 * Clase Heradda de Thread
	 * Maneja el hilo asignado al cliente
	 * Lee el comando recibido
	 */
	
	//Conexión
	private Socket socket = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	
	//Sesion
	private String username;
	private boolean authenticated;
	private boolean running; // <-- Conocer si la sesion sigue activa
	private Hashtable<String, ClientHandler> conectedClients;
	
	//Protocolo de comandos
	private ProtocolParser parser;

	public ClientHandler(Socket socket, Hashtable<String, ClientHandler> list) {
		this.socket = socket;
		this.conectedClients = list;
		this.username = "anonymous";
		this.authenticated = false;
		this.running = true;
		this.parser = new ProtocolParser(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void run() {
		
		System.out.println("Conexión establecida.");
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String recivedLine = "";
			
			while(running) {
				
				recivedLine = br.readLine();
				
				System.out.println("Cliente: " + recivedLine);
				
				String response = parser.processCommand(recivedLine);
				
				pw.println(response); pw.flush();
				
				if (!running) break;
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeSesion();
	    }

	}
	
	public void addThread(String user) {
		conectedClients.put(user, this);
	}
	
	public void stopHandler() {
        this.running = false;
    }
	
	private void closeSesion() {
        try {
        	
            if (this.username != null && conectedClients.containsKey(this.username)) {
            	conectedClients.remove(this.username);
                System.out.println("Usuario " + this.username + " eliminado de la lista.");
            }

            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            
            System.out.println("Recursos liberados para el hilo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
