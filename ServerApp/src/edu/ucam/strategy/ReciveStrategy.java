package edu.ucam.strategy;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.function.Consumer;

import edu.ucam.interfaces.IDataStrategy;

public class ReciveStrategy<T> implements IDataStrategy {
	
	private Consumer<T> action;

    public ReciveStrategy(Consumer<T> action) {
    	this.action = action;
    }

	@Override
	public void execute(Socket socket) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        T data = (T) ois.readObject();
     
        action.accept(data);
        
        System.out.println("Objeto procesado.");
		
	}

}
