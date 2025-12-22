package edu.ucam.logic.command.tit;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class RemoveTitCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		TitulacionDAO dao = DAOFactory.getInstance().getTitulacionDAO();
		
		if(dao.eliminar(cp.getParam(0))) {
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        204, 
	    	        "Titulacion Eliminada Corectamente."
					).toProtocolString();
		}
		
		return new ProtocolResponse(
    	        ProtocolResponse.Status.FAILED, 
    	        cp.getId(), 
    	        404, 
    	        "El objeto a alimiar no existe"
				).toProtocolString();
	}

}
