package edu.ucam.logic;

import edu.ucam.data.UniversityRepository;
import edu.ucam.threads.ClientHandler;

public class ProtocolParser {
	
	/*
	 * Maneja el parseo de la cadena de conexión
	 * Interactua con los datos
	 * Switch para decidir cada acción
	 */
	
	private ClientHandler clientHandler;
    private UniversityRepository repo;

    public ProtocolParser(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.repo = UniversityRepository.getInstance();
    }
	
	public String processCommand(String recivedLine) {
		
		String partes[] = recivedLine.split(" ");
		    
	    if (partes.length < 2) return "FAILED 0 400 Comando incompleto o vacío";
		
		String idEnvio = partes[0];
	    String comando = partes[1].toUpperCase();
	    
	    //Comprobar si el usaurio esta loggeado
	    
	    if(noAuthorized(comando)) return new ProtocolResponse(
				ProtocolResponse.Status.FAILED, 
				idEnvio,
				401,
				"Acceso denegado. Debe iniciar sesión primero."
				).toProtocolString();
	    
	    
	    try {
	    	switch (comando) {
		    
		    case "USER":
		    	
		    	if(repo.existUser(partes[2])) {
		    		
		    		clientHandler.setUsername(partes[2]);
		    		
		    		 return new ProtocolResponse(
			                ProtocolResponse.Status.OK, 
			                idEnvio, 
			                200,
			                "Envie Contraseña"
			            ).toProtocolString();	
		    	}
		    	
		    	 return new ProtocolResponse(
		                ProtocolResponse.Status.FAILED, 
		                idEnvio, 
		                400,
		                "Not user."
		            ).toProtocolString();
		        
		    case "PASS":
		    	
		    	if(repo.validarUsuario(clientHandler.getUsername(), partes[2])) {
		    		
		    		clientHandler.addThread(clientHandler.getUsername());
		    		clientHandler.setAuthenticated(true);
		    		
		    		return new ProtocolResponse(
			                ProtocolResponse.Status.OK, 
			                idEnvio, 
			                200,
			                "Welcome"
			            ).toProtocolString();	
		    	}
		    	return new ProtocolResponse(
		                ProtocolResponse.Status.FAILED, 
		                idEnvio, 
		                400,
		                "Pruebe de nuevo."
		            ).toProtocolString();	
		        
		    case "EXIT":
		    	
		    	clientHandler.stopHandler();
		    	
		    	return new ProtocolResponse(
		    	        ProtocolResponse.Status.OK, 
		    	        idEnvio, 
		    	        200, 
		    	        "Adios"
		    	    ).toProtocolString();
		    	
		    case "SESIONES":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "ADDTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "UPDATETIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "GETTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "REMOVETIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "LISTTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "COUNTTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "ADDASIG":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "GETASIG":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "REMOVEASIG":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "LISTASIG":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "ADDASIG2TIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "REMOVEASIGFROMTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "LISTASIGFROMTIT":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "ADDMATRICULA":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "UPDATEMATRICULA":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "GETMATRICULA":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    case "REMOVEMATRICULA":
		        return "OK " + idEnvio + " 200 En desarrollo";
		    default:
		        return "FAILED " + idEnvio + " 400 Comando desconocido";
	    	}
	    	
	    }catch(Exception e) {
	    	
	    	String mensajeError = e.getMessage();
	    	
	    	ProtocolResponse respuestaError = new ProtocolResponse(
	                ProtocolResponse.Status.FAILED, 
	                idEnvio, 
	                400,
	                mensajeError
	            );

	            return respuestaError.toProtocolString();
	    	
	    }  
	}
	
	public boolean noAuthorized(String comando) {
		boolean esComandoPublico = comando.equals("USER") || 
                comando.equals("PASS") || 
                comando.equals("EXIT");

		return !esComandoPublico && !clientHandler.isAuthenticated();
	}

}
