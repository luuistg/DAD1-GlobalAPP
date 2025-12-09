package edu.ucam.cliente.repository;

import java.io.IOException;
import java.util.List;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.interfaces.IRepository;
import edu.ucam.cliente.service.ResponseParser;

public abstract class BaseRepository<T> implements IRepository<T>{
	
    protected IComunicationServer comm;  // Canal 5000
    protected IChannelData channelData;  // Canal 5001+
    protected ResponseParser parser;

    public BaseRepository(IComunicationServer comm, IChannelData channelData) {
        this.comm = comm;
        this.channelData = channelData;
    }
	
	
	protected abstract String getAddCommand();
    protected abstract String getDeleteCommand();
    protected abstract String getListCommand();
    protected abstract String getUpdateCommand();
    protected abstract String getGetCommand();

	@Override
	public void add(Object model) throws IOException, ClassNotFoundException {

		//Envio el commando con commsendcomand()
		parser = new ResponseParser(comm.sendCommand(getAddCommand()));
		
		if(parser.isPREOK()) {
			
			channelData.sendObject(parser.getIp(), parser.getPort() , model);
			comm.receiveMessage();
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			
		}
	}

	@Override
	public void delete(String id) throws IOException {
		parser = new ResponseParser(comm.sendCommand(this.getDeleteCommand() + " " + id));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}
		
		System.out.println("Success: " + parser.getMessage());
	}

	@Override
	public List list() throws IOException, ClassNotFoundException {
		parser = new ResponseParser(comm.sendCommand(getAddCommand()));
		
		if(parser.isPREOK()) {
			
			List models = (List) channelData.receiveObject(parser.getIp(), parser.getPort());
			comm.receiveMessage();
			return models;
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			return null;
		}
	}
	

	@Override
	public void update(String id, Object model) throws IOException, ClassNotFoundException {
		parser = new ResponseParser(comm.sendCommand(getUpdateCommand() + " " + model));
		
		if(parser.isPREOK()) {
			
			channelData.sendObject(parser.getIp(), parser.getPort() , model);
			comm.receiveMessage();
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			
		}
		
	}

	@Override
	public T getModel(String id) throws IOException, ClassNotFoundException {
		parser = new ResponseParser(comm.sendCommand(getAddCommand() + " " + id));
		
		if(parser.isPREOK()) {
			
			T model = (T) channelData.receiveObject(parser.getIp(), parser.getPort());
			comm.receiveMessage();
			return model;
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			return null;
		}
	}
}
