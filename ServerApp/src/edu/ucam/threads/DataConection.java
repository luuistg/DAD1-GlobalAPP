package edu.ucam.threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.ucam.interfaces.IDataStrategy;
import edu.ucam.util.PortManager;

public class DataConection extends Thread{
	
	/*
	 * Hilo para envio de objetos
	 * Uso limitado y temporal
	 */
	
	private int port;
	private String ip = "127.0.0.1";
    private ServerSocket serverSocket;
    private IDataStrategy strategy;
    
    // CALLBACKS: Acciones a ejecutar al terminar
    private Runnable onSuccess; 
    private Runnable onError;

    // Constructor: Recibe la estrategia (Polimorfismo)
    public DataConection(IDataStrategy strategy, Runnable onSuccess, Runnable onError) throws Exception {
        this.strategy = strategy;
        this.onSuccess = onSuccess;
        this.onError = onError;
        
        this.port = new PortManager().searchFreePort();
        this.serverSocket = new ServerSocket(this.port);
    }

    public int getPort() {
        return port;
    }
    
    public String getIp() {
    	return this.ip;
    }

    @Override
    public void run() {
        try (ServerSocket ss = this.serverSocket) {

            Socket clientSocket = ss.accept();
            
            // EJECUTAR LA ESTRATEGIA
            strategy.execute(clientSocket);
            
            clientSocket.close();
            
            if (onSuccess != null) onSuccess.run();
            
        } catch (Exception e) {
            System.err.println("Error en hilo de datos: " + e.getMessage());
            if (onError != null) onError.run();
        }
    }

}
