package edu.ucam.logic.command.asig;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.AsignaturaDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class RemoveAsigCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		AsignaturaDAO dao = DAOFactory.getInstance().getAsignaturaDAO();
		
		if(dao.eliminar(cp.getParam(0))) {
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        204, 
	    	        "AsignaturaDAO Eliminada Corectamente."
					).toProtocolString();
		}
		
		return new ProtocolResponse(
    	        ProtocolResponse.Status.FAILED, 
    	        cp.getId(), 
    	        404, 
    	        "El objeto a elimiar no existe"
				).toProtocolString();

	}

}
