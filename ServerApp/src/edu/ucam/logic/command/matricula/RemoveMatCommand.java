package edu.ucam.logic.command.matricula;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.MatriculaDAO;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.threads.ClientHandler;

public class RemoveMatCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		
		MatriculaDAO dao = DAOFactory.getInstance().getMatriculaDAO();
		
		if(dao.eliminar(cp.getParam(0))) {
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.OK, 
	    	        cp.getId(), 
	    	        204, 
	    	        "Matricula Eliminada Corectamente."
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
