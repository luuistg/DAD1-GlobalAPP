package edu.ucam.logic.command.tit;

import java.util.List;

import edu.ucam.dao.DAOFactory;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.ICommand;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.logic.CommandParser;
import edu.ucam.logic.ProtocolResponse;
import edu.ucam.strategy.SendStrategy;
import edu.ucam.threads.ClientHandler;
import edu.ucam.threads.DataConection;

public class ListTitCommand implements ICommand{

	@Override
	public String execute(CommandParser cp, ClientHandler cl) {
		try {
			
			TitulacionDAO dao = DAOFactory.getInstance().getTitulacionDAO();
			
			List<Titulacion> list = dao.listar();
			
			if(list == null) {
					return new ProtocolResponse(
	    	        ProtocolResponse.Status.FAILED, 
	    	        cp.getId(), 
	    	        404, 
	    	        "No existen Titulaciones"
					).toProtocolString();
			}
			
			SendStrategy strategy = new SendStrategy(list);
			
			//DEFINIMOS LOS CALLBACKS
            
            Runnable onSuccess = () -> {
                cl.sendFinalMSG(new ProtocolResponse(
    	    	        ProtocolResponse.Status.OK, 
    	    	        cp.getId(), 
    	    	        200, 
    	    	        "Titulaciónes enviadas correctamente"
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
    	        "Error al enviar la Titulación"
				).toProtocolString();
	}
}
