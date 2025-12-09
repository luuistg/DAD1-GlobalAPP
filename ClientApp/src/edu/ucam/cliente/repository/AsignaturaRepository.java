package edu.ucam.cliente.repository;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;

public class AsignaturaRepository extends BaseRepository{

	public AsignaturaRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getAddCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDeleteCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getListCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getGetCommand() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addAsigToTit() {
		
	}
	
	public void removeAsigToTit() {
		
	}
	
	public void listAsigFromTit() {
		
	}

}
