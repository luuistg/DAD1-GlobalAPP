package edu.ucam.logic;

import java.util.HashMap;
import java.util.Map;

import edu.ucam.interfaces.ICommand;
import edu.ucam.logic.command.LogInCommand;
import edu.ucam.logic.command.PassCommand;
import edu.ucam.logic.command.asig.AddAsigCommand;
import edu.ucam.logic.command.asig.AddAsigToTitCommand;
import edu.ucam.logic.command.asig.GetAsigCommand;
import edu.ucam.logic.command.asig.ListAsigCommand;
import edu.ucam.logic.command.asig.ListAsigFromTitCommand;
import edu.ucam.logic.command.asig.RemoveAsigCommand;
import edu.ucam.logic.command.asig.RemoveAsigFromTitCommand;
import edu.ucam.logic.command.asig.UpdateAsigCommand;
import edu.ucam.logic.command.matricula.AddMatCommand;
import edu.ucam.logic.command.matricula.GetMatCommand;
import edu.ucam.logic.command.matricula.RemoveMatCommand;
import edu.ucam.logic.command.matricula.UpdateMatCommand;
import edu.ucam.logic.command.tit.AddTitCommand;
import edu.ucam.logic.command.tit.CountTitsCommand;
import edu.ucam.logic.command.tit.GetTitCommand;
import edu.ucam.logic.command.tit.ListTitCommand;
import edu.ucam.logic.command.tit.RemoveTitCommand;
import edu.ucam.logic.command.tit.UpdateTitCommand;
import edu.ucam.threads.ClientHandler;

public class ProtocolParser {
	
	/*
	 * Maneja el parseo de la cadena de conexión
	 * Interactua con los datos
	 * Switch para decidir cada acción
	 */
	
	private ClientHandler clientHandler;
    private Map<String, ICommand> comands;

    public ProtocolParser(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.comands = new HashMap<>();
        inicializarComandos();
    }
    
    private void inicializarComandos() {
        // Registramos cada palabra clave con su obrero especialista
        comands.put("USER", new LogInCommand());
        comands.put("PASS", new PassCommand());
        
        // Comandos de Titulaciones
        comands.put("ADDTIT", new AddTitCommand());
        comands.put("GETTIT", new GetTitCommand());
        comands.put("LISTTIT", new ListTitCommand());
        comands.put("REMOVETIT", new RemoveTitCommand());
        comands.put("COUNTTIT", new CountTitsCommand());
        comands.put("UPDATETIT", new UpdateTitCommand());
        
        
        // Comandos de Asignaturas
        comands.put("ADDASIG", new AddAsigCommand());
        comands.put("LISTASIG", new ListAsigCommand());
        comands.put("GETASIG", new GetAsigCommand());
        comands.put("REMOVEASIG", new RemoveAsigCommand());
        comands.put("UPDATEASIG", new UpdateAsigCommand());
        comands.put("ADDASIG2TIT", new AddAsigToTitCommand());
        comands.put("REMOVEASIGFROMTIT", new RemoveAsigFromTitCommand());
        comands.put("LISTASIGFROMTIT", new ListAsigFromTitCommand());
        
        
        // Comandos de Matriculas
        comands.put("ADDMATRICULA", new AddMatCommand());
        comands.put("GETMATRICULA", new GetMatCommand());
        comands.put("REMOVEMATRICULA", new RemoveMatCommand());
        comands.put("UPDATEMATRICULA", new UpdateMatCommand());
        
        
        
    }
	
	public String processCommand(String recivedLine) {
		
		if (recivedLine == null || recivedLine.trim().isEmpty()) return "FAILED 0 400 Comando incompleto o vacío";
		
		String partes[] = recivedLine.split(" ");
		
		CommandParser request = new CommandParser(partes);
		
		//Comprobar si el usaurio esta loggeado
	    
	    if(noAuthorized(request.getName())) return new ProtocolResponse(
				ProtocolResponse.Status.FAILED, 
				request.getId(),
				401,
				"Acceso denegado. Debe iniciar sesión primero."
				).toProtocolString();
		
	    if (request.getName().equals("EXIT")) {
	        // Avisamos al ClientHandler que debe dejar de escuchar
	        clientHandler.stopHandler(); 
	        
	        // Devolvemos el mensaje de despedida (OK)
	        return new ProtocolResponse(
	            ProtocolResponse.Status.OK,
	            request.getId(),
	            200,
	            "BYE"
	        ).toProtocolString();
	    }
	    
		if (comands.containsKey(request.getName())) {
            
		    try {
		    	
	            return comands.get(request.getName()).execute(request, clientHandler);
		    	
		    	
		    }catch(Exception e) {
		    	
		    	String mensajeError = e.getMessage();
		    	
		    	ProtocolResponse respuestaError = new ProtocolResponse(
		                ProtocolResponse.Status.FAILED, 
		                request.getId(), 
		                400,
		                mensajeError
		            );
	
		            return respuestaError.toProtocolString();
		    	
		    }  
		}
		
		return new ProtocolResponse(
		        ProtocolResponse.Status.FAILED, 
		        request.getId(), 
		        404,
		        "Comando desconocido: " + request.getName()
		    ).toProtocolString();
	}
	
	public boolean noAuthorized(String comando) {
		boolean esComandoPublico = comando.equals("USER") || 
                comando.equals("PASS") || 
                comando.equals("EXIT");

		return !esComandoPublico && !clientHandler.isAuthenticated();
	}

}
