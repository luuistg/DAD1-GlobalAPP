package edu.ucam.cliente.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.ucam.cliente.interfaces.IChannelData;

public class ChannelDataSocket implements IChannelData{
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	@Override
	public void sendObject(String ip, int port, Object model) throws IOException, ClassNotFoundException {
		connect(ip, port);		
		oos.writeObject(model);
	}

	@Override
	public Object receiveObject(String ip, int port) throws IOException, ClassNotFoundException{
		connect(ip, port);
		return ois.readObject();
	}
	
	private void connect(String ip, int port) throws IOException, ClassNotFoundException{
		
		this.socket = new Socket(ip, port);
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		
	}

}
