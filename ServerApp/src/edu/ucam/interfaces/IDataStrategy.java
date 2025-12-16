package edu.ucam.interfaces;

import java.net.Socket;

public interface IDataStrategy {
	void execute(Socket socket) throws Exception;

}
