package edu.ucam.cliente.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.ucam.cliente.interfaces.IChannelData;

public class ChannelDataSocket implements IChannelData{
	
	@Override
    public void sendObject(String ip, int port, Object model) throws IOException {
        System.out.println("> Conectando para enviar a " + ip + ":" + port);

        try (Socket s = new Socket(ip, port);
             ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())) {

            oos.writeObject(model);
            oos.flush();
            System.out.println("> Objeto enviado correctamente.");
            
        }
    }

    @Override
    public Object receiveObject(String ip, int port) throws IOException, ClassNotFoundException {
        System.out.println("> Conectando para recibir de " + ip + " : " + port);

        try (Socket s = new Socket(ip, port);
             ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {

            Object recibido = ois.readObject();
            System.out.println("> Objeto recibido correctamente.");
            return recibido;

        }
    }

}
