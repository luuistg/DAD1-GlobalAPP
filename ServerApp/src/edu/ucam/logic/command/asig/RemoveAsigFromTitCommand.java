package edu.ucam.logic.command.asig;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.AsignaturaDAO;
import edu.ucam.interfaces.ICommand;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class RemoveAsigFromTitCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		AsignaturaDAO dao = DAOFactory.getInstance().getAsignaturaDAO();
		
		
		if(dao.removeAsigToTit(cp.getParam(0), cp.getParam(1))) {
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        204, 
	    	        "Asignatura eliminada Corectamente de la Titulacion."
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
