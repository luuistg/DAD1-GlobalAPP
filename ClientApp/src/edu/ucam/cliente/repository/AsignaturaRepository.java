package edu.ucam.cliente.repository;

import java.io.IOException;
import java.util.List;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.service.ResponseParser;
import edu.ucam.domain.Asignatura;

public class AsignaturaRepository extends BaseRepository <Asignatura>{
	
	private final String addAsigToTit = "ADDASIG2TIT";
	private final String removeAsigFromTit = "REMOVEASIGFROMTIT";
	private final String listAsigFromTit = "LISTASIGFROMTIT";

	public AsignaturaRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData, 
	              "ADDASIG",         
	              "REMOVEASIG",         
	              "LISTASIG",        
	              "UPDATEASIG",         
	              "GETASIG"          
	        );
	}


	
	public void addAsigToTit(String idAsig, String idTit) throws IOException {

		parser = new ResponseParser(comm.sendCommand(addAsigToTit  + " " + idAsig + " " + idTit));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}else {
			System.out.println("Success: " + parser.getMessage());
		}
	}
	
	public void removeAsigToTit(String idAsig, String idTit) throws IOException{
		
		parser = new ResponseParser(comm.sendCommand(removeAsigFromTit  + " " + idAsig + " " + idTit));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}else {
			System.out.println("Success: " + parser.getMessage());
		}
	}
	
	public List<Asignatura> listAsigFromTit(String idTit) throws IOException, ClassNotFoundException{
		
		parser = new ResponseParser(comm.sendCommand(listAsigFromTit + " " + idTit));
		
		if(parser.isPREOK()) {
			
			List<Asignatura> models = (List) channelData.receiveObject(parser.getIp(), parser.getPort());
			comm.receiveMessage();
			return models;
			
		}else{
			System.out.println("Error: " + parser.getMessage());
			return null;
		}
		
	}

}
