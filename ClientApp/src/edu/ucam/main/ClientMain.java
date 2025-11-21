package edu.ucam.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import edu.ucam.controller.ClientController;
import edu.ucam.ui.Interface;

public class ClientMain {

	/*
	 * Punto de entrada para el cliente
	 * SOcket al puerto 5000
	 * Intancia la consola UI
	 */
	
	private static final String IP = "127.0.0.1"; 
    private static final int PORT = 8000;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inicializando aplicación cliente...");

        ClientController controller = new ClientController(IP, PORT);
        
        Interface ui = new Interface(controller);
        ui.iniciar();
        
        System.out.println("Aplicación finalizada.");

	}

}
