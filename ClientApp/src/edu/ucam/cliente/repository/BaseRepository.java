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
    
    protected final Class<T> type;
    protected final String cmdAdd;     
    protected final String cmdDelete;   
    protected final String cmdList;     
    protected final String cmdUpdate;   
    protected final String cmdGet;      

    public BaseRepository(IComunicationServer comm, IChannelData channelData, Class<T> type, 
            String cmdAdd, String cmdDelete, String cmdList, 
            String cmdUpdate, String cmdGet) {

		this.comm = comm;
		this.channelData = channelData;
		
		// Guardamos la configuración
		this.type = type;
		this.cmdAdd = cmdAdd;
		this.cmdDelete = cmdDelete;
		this.cmdList = cmdList;
		this.cmdUpdate = cmdUpdate;
		this.cmdGet = cmdGet;
	}

	@Override
	public void add(Object model) throws IOException, ClassNotFoundException {

		//Envio el commando con commsendcomand()
		parser = new ResponseParser(comm.sendCommand(this.cmdAdd));
		
		if(parser.isPREOK()) {
			
			channelData.sendObject(parser.getIp(), parser.getPort() , model);
			
			comm.receiveMessage();
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			
		}
	}

	@Override
	public void delete(String id) throws IOException {
		parser = new ResponseParser(comm.sendCommand(this.cmdDelete + " " + id));
		
		if(!parser.isSuccess()) {
			System.out.println("Error: " + parser.getMessage());
		}
		
		System.out.println("Success: " + parser.getMessage());
	}

	@Override
	public List list() throws IOException, ClassNotFoundException {
		parser = new ResponseParser(comm.sendCommand(cmdList));
		
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
		parser = new ResponseParser(comm.sendCommand(cmdUpdate + " " + model));
		
		if(parser.isPREOK()) {
			
			channelData.sendObject(parser.getIp(), parser.getPort() , model);
			comm.receiveMessage();
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			
		}
		
	}

	@Override
	public T getModel(String id) throws IOException, ClassNotFoundException {
		parser = new ResponseParser(comm.sendCommand(cmdGet + " " + id));
		
		if(parser.isPREOK()) {
			
			T model = (T) channelData.receiveObject(parser.getIp(), parser.getPort());
			comm.receiveMessage();
			
			type.cast(model);
			
			System.out.println("[Recibido] -> " +  model.toString());
			return model;
			
		}else{
			System.out.println("Error al conectarse al servidor" + parser.getMessage());
			return null;
		}
	}
}
