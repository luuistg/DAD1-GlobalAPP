package edu.ucam.cliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.interfaces.IUserInterface;
import edu.ucam.cliente.repository.AsignaturaRepository;
import edu.ucam.cliente.repository.BaseRepository;
import edu.ucam.cliente.repository.TitulacionRepository;
import edu.ucam.cliente.interfaces.IForm;

public class ClienteERP {
	
	private IComunicationServer comm;
    private IChannelData data;
    private IUserInterface ui;
    
    @SuppressWarnings("rawtypes")
	HashMap<Integer, BaseRepository> repoList = new HashMap<>();
    HashMap<Integer, IForm> formList = new HashMap<>();
    
	Scanner sc;
	
	public ClienteERP(IComunicationServer comm, IChannelData data, IUserInterface ui, HashMap repo, HashMap form, Scanner sc) {
        this.comm = comm;
        this.data = data;
        this.ui = ui;
        this.sc = new Scanner(System.in);
        this.repoList = repo;
        this.formList = form;
        this.sc = sc;
    }
	
	public void iniciar() {
		boolean exit = false;
		int opt;

        while (!exit) {
            try {
            	opt = Integer.parseInt(ui.showMainMenu(sc));
            	
            	if(opt == 0) {
            		exit = true;
            		
            		comm.sendCommand("EXIT");
            		continue;
            	}
            		
            	if(repoList.containsKey(opt)) {
            		getExactMethod(opt);
            	}else {
            		System.out.println("Opción incorrecta.");
	
            	}
            } catch (Exception e) {
                System.err.println("Error en la operación: " + e.getMessage());
            }
        }
        
        System.out.println("Hasta luego!");
        try {
			comm.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	public void getExactMethod(int opt) {
		
		BaseRepository repo = repoList.get(opt);
		IForm form = formList.get(opt);
		
		try {
			
			ui.showOptMenu();
			if(repo instanceof TitulacionRepository) ui.showTitOption();
			if(repo instanceof AsignaturaRepository) ui.showAsigOptions();
			int key = Integer.parseInt(ui.showchooseMenu(sc));
			
			if(key > 5 && !(repo instanceof TitulacionRepository) && !(repo instanceof AsignaturaRepository)) key = -1;
			
			switch(key) {
			
			case 1:
				repo.add(form.addForm());
				break;
				
			case 2:
				repo.delete(null);
				break;
				
			case 3:
				repo.list();
				break;
				
			case 4:
				String id = form.getForm();
				repo.update(id,form.updateForm(id));
				break;
				
			case 5:
				repo.getModel(form.getForm());
				break;
			case 6:
				if(repo instanceof TitulacionRepository) {
					((TitulacionRepository) repo).modelSize();
				}
				((AsignaturaRepository) repo).addAsigToTit();
				break;
			case 7:
				((AsignaturaRepository) repo).removeAsigToTit();
				break;
			case 8:
				((AsignaturaRepository) repo).listAsigFromTit();
				break;
			case 0:
				
				break;
				
			default:
				System.out.println("Opción incorrecta.");	
			}
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
