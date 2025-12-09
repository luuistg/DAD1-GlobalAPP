package edu.ucam.cliente.repository;

import java.io.IOException;

import edu.ucam.cliente.exceptions.NullResponseException;
import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.service.ResponseParser;
import edu.ucam.domain.Titulacion;

public class TitulacionRepository extends BaseRepository <Titulacion>{

	public TitulacionRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getAddCommand() {
		// TODO Auto-generated method stub
		return "ADDTIT";
	}

	@Override
	protected String getDeleteCommand() {
		// TODO Auto-generated method stub
		return "GETTIT";
	}

	@Override
	protected String getListCommand() {
		// TODO Auto-generated method stub
		return "LISTTIT";
	}

	@Override
	protected String getUpdateCommand() {
		// TODO Auto-generated method stub
		return "UPDATETIT";
	}

	@Override
	protected String getGetCommand() {
		// TODO Auto-generated method stub
		return "GETTIT";
	}
	
	public void modelSize() throws NullResponseException, IOException {
		
		parser = new ResponseParser(comm.sendCommand("COUNTTIT"));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}
		
		System.out.println("Success: " + parser.getMessage());

	}
}
