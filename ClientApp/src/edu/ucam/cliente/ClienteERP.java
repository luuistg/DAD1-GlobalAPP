package edu.ucam.cliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import edu.ucam.cliente.factory.ServiceFactory;
import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.interfaces.IUserInterface;
import edu.ucam.cliente.service.AsignaturaService;
import edu.ucam.cliente.service.MatriculaService;
import edu.ucam.cliente.service.TitulacionService;
import edu.ucam.cliente.interfaces.IForm;
import edu.ucam.cliente.interfaces.IGenericService;

public class ClienteERP {
	
	private IComunicationServer comm;
    private IChannelData data;
    private IUserInterface ui;
    
    HashMap<Integer, IForm> formList = new HashMap<>();
    
	Scanner sc;
	
	public ClienteERP(IComunicationServer comm, IChannelData data, IUserInterface ui, HashMap form, Scanner sc) {
        this.comm = comm;
        this.data = data;
        this.ui = ui;
        this.sc = sc;
        this.formList = form;
        this.sc = sc;
        
        ServiceFactory.init(comm, data);
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
            	
            	if(ServiceFactory.getInstance().getService(opt) != null) {
            		getExactMethod(opt);
            	} else {
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
	
	public void getExactMethod(int opt) throws Exception {
		
		IGenericService<?, ?> service = ServiceFactory.getInstance().getService(opt);
		
		IForm form = formList.get(opt);
		
		try {
			
			ui.showOptMenu();
			if(service instanceof TitulacionService) ui.showTitOption();
			if(service instanceof AsignaturaService) ui.showAsigOptions();
			
			int key = Integer.parseInt(ui.showchooseMenu(sc));
			
			if (service instanceof TitulacionService && key > 6) {
			    key = -1;
			} 
			else if (service instanceof AsignaturaService && key > 8) {
			    key = -1;
			}
			else if (service instanceof MatriculaService && key > 4) {
			    key = -1; 
			}
			
			switch(key) {
			
			case 1:
				service.add(form.addForm(sc));
				break;
				
			case 2:
				service.delete(form.getForm(sc));
				break;
				
			case 3:
				String id = form.getForm(sc);
				service.update(id,form.updateForm(id, sc));
				break;
				
			case 4:
				
				service.get(form.getForm(sc));
				break;
				
			case 5:
				service.list();
				break;
				
			case 6:
				if(service instanceof TitulacionService) {
					((TitulacionService) service).count();
				} 
				else{
					((AsignaturaService) service).addAsigToTit();
				}
				break;
			case 7:
				((AsignaturaService) service).removeAsigToTit();
				break;
			case 8:
				((AsignaturaService) service).listAsigFromTit();
				break;
			case 0:
				
				break;
				
			default:
				System.out.println("Opción incorrecta.");	
			}
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
