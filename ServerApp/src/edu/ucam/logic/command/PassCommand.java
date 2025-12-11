package edu.ucam.logic.command;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.UserDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class PassCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		UserDAO dao = DAOFactory.getInstance().getUsuarioDAO();
		

		if(dao.checkPass(cl.getUsername(), cp.getParam(0))) {
    		
    		cl.addThread(cl.getUsername());
    		cl.setAuthenticated(true);
    		
    		return new ProtocolResponse(
	                ProtocolResponse.Status.OK, 
	                cp.getId(), 
	                200,
	                "Welcome"
	            ).toProtocolString();	
    	}
		
    	return new ProtocolResponse(
                ProtocolResponse.Status.FAILED, 
                cp.getId(), 
                400,
                "Pruebe de nuevo."
            ).toProtocolString();
	}

}
