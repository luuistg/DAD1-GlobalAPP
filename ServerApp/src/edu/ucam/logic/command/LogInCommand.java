package edu.ucam.logic.command;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.UserDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class LogInCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		UserDAO dao = DAOFactory.getInstance().getUsuarioDAO();
		
		if(dao.getUser(cp.getParam(0))) {
    		
    		cl.setUsername(cp.getParam(0));
    		
    		 return new ProtocolResponse(
	                ProtocolResponse.Status.OK, 
	                cp.getId(), 
	                200,
	                "Envie Contraseña"
	            ).toProtocolString();	
    	}
    	
    	 return new ProtocolResponse(
                ProtocolResponse.Status.FAILED, 
                cp.getId(), 
                400,
                "Not user."
            ).toProtocolString();
	}

}
