package edu.ucam.strategy;

import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.ucam.interfaces.IDataStrategy;

public class SendStrategy implements IDataStrategy {

    private Object dataToSend;

    public SendStrategy(Object dataToSend) {
        this.dataToSend = dataToSend;
    }

    @Override
    public void execute(Socket socket) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        
        oos.writeObject(dataToSend);
        dataToSend.toString();
        oos.flush();
        
        System.out.println("Datos enviados al cliente.");
    }

}
