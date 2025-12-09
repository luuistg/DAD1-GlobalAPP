package edu.ucam.cliente.service;

import java.io.IOException;

import edu.ucam.cliente.interfaces.IAutentication;
import edu.ucam.cliente.interfaces.IComunicationServer;

public class AutenticationService implements IAutentication {
	
	private IComunicationServer comm;

    // Constructor: Inyectamos el servicio de comunicación YA CONECTADO
    public AutenticationService(IComunicationServer comm) {
        this.comm = comm;
    }

	@Override
	public boolean autenticar(String usuario, String password) throws IOException {
		// TODO Auto-generated method stub
		
		String response = comm.sendCommand("USER "+ usuario);
		
		if (response == null || !response.startsWith("OK")) {
            System.err.println("Error en usuario: " + response);
            return false;
        }

        String responsePass = comm.sendCommand("PASS " + password);
        
        // Protocolo: Si empieza por OK, estamos dentro
        if (responsePass != null && responsePass.startsWith("OK")) {
        	System.out.println("Autenticación correcta.");
            return true;
        } else {
            System.err.println("Error en contraseña: " + responsePass);
            return false;
        }
	}

	@Override
	public void closeSession() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
