package edu.ucam.cliente;

import java.util.Scanner;

import edu.ucam.cliente.config.ClientConfig;
import edu.ucam.cliente.service.AutenticationService;
import edu.ucam.cliente.service.ChannelDataSocket;
import edu.ucam.cliente.service.CommunicationSocket;
import edu.ucam.cliente.ui.UserInterface;
import edu.ucam.cliente.interfaces.*;

public class ClientePrincipal {
	
	public static void main(String args[]) {
		
		try {
			
			Scanner sc = new Scanner(System.in);

            IComunicationServer comm = new CommunicationSocket();
            comm.connect();
            
            IChannelData data = new ChannelDataSocket();
            
            IUserInterface ui = new UserInterface();
            
            IAutentication aut = new AutenticationService(comm);
            
            //Autenticamos antes de la inyeccion a clienteERP
            
            if(!aut.autenticar(ui.askUser(sc), ui.askPass(sc))) {
            	comm.disconnect();
            	System.exit(0);
            	
            }

            var mapRepos = ClientConfig.initRepositories(comm, data);
            var mapForms = ClientConfig.initForms();

            ClienteERP app = new ClienteERP(comm, data, ui, mapRepos, mapForms, sc);
            
            app.iniciar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
