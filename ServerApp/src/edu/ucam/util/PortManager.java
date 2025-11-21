package edu.ucam.util;

import java.io.IOException;
import java.net.ServerSocket;

public class PortManager {
	
	/*
	 * Busqueda de puerto libre 
	 */
	
	private static final int INITIAL_PORT = 5001;
    private static final int END_PORT = 65535;
    
    public static int searchFreePort() throws Exception {
    	
    	for (int port = INITIAL_PORT; port <= END_PORT; port ++){
    		
    		//Usamos try-with-resource
    		//El recurso se cerrar depeus de la declareacion de este
    	    try(ServerSocket socketTest = new ServerSocket(port)){
    	    	
    	    	socketTest.setReuseAddress(true);
    	    	return port;
    	    	
    	    } catch (IOException e) {

			}
    	}
    	
    	throw new Exception("No hay puertos disponibles en este rango.");
    }
    
    

}
