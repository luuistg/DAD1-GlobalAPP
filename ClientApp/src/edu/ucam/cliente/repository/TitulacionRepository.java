package edu.ucam.cliente.repository;

import java.io.IOException;

import edu.ucam.cliente.exceptions.NullResponseException;
import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.service.ResponseParser;
import edu.ucam.domain.Titulacion;

public class TitulacionRepository extends BaseRepository <Titulacion>{

	public TitulacionRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData, 
	              "ADDTIT",         
	              "DELTIT",         
	              "LISTTIT",        
	              "UPDATETIT",         
	              "GETTIT"          
	        );
	}

	
	public void modelSize() throws NullResponseException, IOException {
		
		parser = new ResponseParser(comm.sendCommand("COUNTTIT"));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}
		
		System.out.println("Success: " + parser.getMessage());

	}
}
