package edu.ucam.logic.command;

import java.util.function.Consumer;

import edu.ucam.dao.DAOFactory;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.strategy.ReciveStrategy;
import edu.ucam.threads.ClientHandler;
import edu.ucam.threads.DataConection;

public class AddTitCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		try {
			
			TitulacionDAO dao = DAOFactory.getInstance().getTitulacionDAO();
			
			Consumer<Titulacion> action = (t) -> dao.guardar(t);
			
			ReciveStrategy<Titulacion> strategy = new ReciveStrategy<>(action);
			
			//DEFINIMOS LOS CALLBACKS
            
            Runnable onSuccess = () -> {
                cl.sendFinalMSG(new ProtocolResponse(
    	    	        ProtocolResponse.Status.OK, 
    	    	        cp.getId(), 
    	    	        200, 
    	    	        "Titulación enviada correctamente"
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
	    	        202, 
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
    	        "No se pudo agregar la Titulacion."
				).toProtocolString();
	}

}
