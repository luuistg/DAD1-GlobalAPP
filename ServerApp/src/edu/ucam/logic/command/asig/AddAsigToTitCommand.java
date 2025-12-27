package edu.ucam.logic.command.asig;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.AsignaturaDAO;
import edu.ucam.interfaces.ICommand;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class AddAsigToTitCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		AsignaturaDAO dao = DAOFactory.getInstance().getAsignaturaDAO();
		
		
		if(dao.addAsigToTit(cp.getParam(0), cp.getParam(1))) {
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        204, 
	    	        "Asignatura Añdida Corectamente a la Titulacion."
					).toProtocolString();
		}
		
		return new ProtocolResponse(
    	        ProtocolResponse.Status.FAILED, 
    	        cp.getId(), 
    	        404, 
    	        "Asignatura o Titulacion Incorrectas"
				).toProtocolString();

	}

}
