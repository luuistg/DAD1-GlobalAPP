package edu.ucam.logic.command.tit;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class CountTitsCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		TitulacionDAO dao = DAOFactory.getInstance().getTitulacionDAO();
		
		int count = dao.modelSize();
		
		if (count >= 0) {
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        200, 
	    	        "Número de titulaciones: " + count + "."
					).toProtocolString();
		}
		
		return new ProtocolResponse(
				ProtocolResponse.Status.FAILED, 
    	        cp.getId(), 
    	        500, 
    	        "Error al recuperar el número de titulaciones"
				).toProtocolString();
				
		
		
	}

}
