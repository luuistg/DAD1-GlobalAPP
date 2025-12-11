package edu.ucam.logic.command;

import edu.ucam.interfaces.ICommand;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class ExitCommand implements ICommand{


	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		cl.stopHandler();
    	
    	return new ProtocolResponse(
    	        ProtocolResponse.Status.OK, 
    	        cp.getId(), 
    	        200, 
    	        "Adios"
    	    ).toProtocolString();
	}

}
