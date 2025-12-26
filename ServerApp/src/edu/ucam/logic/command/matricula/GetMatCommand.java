package edu.ucam.logic.command.matricula;

import edu.ucam.dao.DAOFactory;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.MatriculaDAO;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.strategy.SendStrategy;
import edu.ucam.threads.ClientHandler;
import edu.ucam.threads.DataConection;

public class GetMatCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
			try {
			
			MatriculaDAO dao = DAOFactory.getInstance().getMatriculaDAO();
			
			if(dao.buscar(cp.getParam(0)) == null) {
					return new ProtocolResponse(
	    	        ProtocolResponse.Status.FAILED, 
	    	        cp.getId(), 
	    	        404, 
	    	        "Matricula con id: " + cp.getParam(0) + " no encontrada"
					).toProtocolString();
			}
			
			SendStrategy strategy = new SendStrategy(dao.buscar(cp.getParam(0)));
			
			//DEFINIMOS LOS CALLBACKS
            
            Runnable onSuccess = () -> {
                cl.sendFinalMSG(new ProtocolResponse(
    	    	        ProtocolResponse.Status.OK, 
    	    	        cp.getId(), 
    	    	        200, 
    	    	        "Matricula enviada correctamente"
    					).toProtocolString());
            };
            
            Runnable onError = () -> {
                cl.sendFinalMSG(new ProtocolResponse(
    	    	        ProtocolResponse.Status.FAILED, 
    	    	        cp.getId(), 
    	    	        500, 
    	    	        "Error en la transferencia de datos"
    					).toProtocolString());
            };
			
			DataConection hilo = new DataConection(strategy, onSuccess, onError);
			
			hilo.start();
			
			return new ProtocolResponse(
	    	        ProtocolResponse.Status.PREOK, 
	    	        cp.getId(), 
	    	        200, 
	    	        hilo.getIp() + " " + hilo.getPort()
					).toProtocolString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ProtocolResponse(
    	        ProtocolResponse.Status.FAILED, 
    	        cp.getId(), 
    	        500, 
    	        "Error al enviar la Matricula"
				).toProtocolString();
	}

}
